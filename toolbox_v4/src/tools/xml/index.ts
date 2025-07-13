import { Code } from '@vicons/tabler';
import { defineTool } from '../tool';
import { translate } from '@/plugins/i18n.plugin';

export const tool = defineTool({
  name: translate('tools.xml.title'),
  path: '/xml',
  description: translate('tools.xml.description'),
  keywords: ['xml'],
  component: () => import('./xml.vue'),
  icon: Code,
  createdAt: new Date('2025-02-01'),
});
