import { translate } from '@/plugins/i18n.plugin';

export function decodeSafeLinksURL(safeLinksUrl: string) {
  if (!safeLinksUrl.match(/\.safelinks\.protection\.outlook\.com/)) {
    throw new Error(translate('tools.safelink-decoder.invalid-safe-links-url'));
  }

  return new URL(safeLinksUrl).searchParams.get('url');
}
