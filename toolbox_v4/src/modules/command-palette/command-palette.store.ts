import { defineStore } from 'pinia';
import _ from 'lodash';
import type { PaletteOption } from './command-palette.types';
import { useToolStore } from '@/tools/tools.store';
import { useFuzzySearch } from '@/composable/fuzzySearch';
import { translate } from '@/plugins/i18n.plugin';
import { starsData } from '@/stars/stars.data';

import DiceIcon from '~icons/mdi/dice-5';
import IconWorld from '~icons/mdi/world';
import type { StarWithCategory } from '@/stars/stars.types';

export const useCommandPaletteStore = defineStore('command-palette', () => {
  const toolStore = useToolStore();
  const router = useRouter();
  const searchPrompt = ref('');

  const toolsOptions = toolStore.tools.map(tool => ({
    ...tool,
    to: tool.path,
    toolCategory: tool.category,
    category: translate('ui.command-palette.tools'),
  }));

  const allStars = starsData.reduce((accumulator, currentCategory) => {
    return accumulator.concat(currentCategory.stars);
  }, [] as StarWithCategory['stars'][number][]);

  const starsOptions = allStars.map(star => ({
    name: star.name,
    href: star.href,
    category: translate('ui.command-palette.stars'),
    description: star.description,
    keywords: [star.name],
    icon: IconWorld,
  }));

  const searchOptions: PaletteOption[] = [
    ...toolsOptions,
    {
      name: translate('ui.command-palette.random-tool'),
      description: translate('ui.command-palette.random-tool-description'),
      action: () => {
        const { path } = _.sample(toolStore.tools)!;
        router.push(path);
      },
      icon: DiceIcon,
      category: translate('ui.command-palette.tools'),
      keywords: ['random', 'tool', 'pick', 'choose', 'select'],
      closeOnSelect: true,
    },
    ...starsOptions,
  ];

  const { searchResult } = useFuzzySearch({
    search: searchPrompt,
    data: searchOptions,
    options: {
      keys: [{ name: 'name', weight: 2 }, 'description', 'keywords', 'category'],
      threshold: 0.3,
    },
  });

  const filteredSearchResult = computed(() =>
    _.chain(searchResult.value).groupBy('category').mapValues(categoryOptions => _.take(categoryOptions, 5)).value());

  return {
    filteredSearchResult,
    searchPrompt,
  };
});
