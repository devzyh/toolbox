import { ArrowsShuffle } from '@vicons/tabler';
import { defineTool } from '../tool';
import { translate } from '@/plugins/i18n.plugin';

export const tool = defineTool({
  name: translate('tools.xslt.title'),
  path: '/xslt',
  description: translate('tools.xslt.description'),
  keywords: ['xslt'],
  component: () => import('./xslt.vue'),
  icon: ArrowsShuffle,
  createdAt: new Date('2025-02-04'),
});
