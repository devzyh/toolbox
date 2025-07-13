import { Braces } from '@vicons/tabler';
import { defineTool } from '../tool';
import { translate } from '@/plugins/i18n.plugin';

export const tool = defineTool({
  name: translate('tools.json.title'),
  path: '/json',
  description: translate('tools.json.description'),
  keywords: ['json'],
  component: () => import('./json.vue'),
  icon: Braces,
  createdAt: new Date('2025-01-31'),
});
