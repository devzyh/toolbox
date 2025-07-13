import { translate } from '@/plugins/i18n.plugin';

export { convert };

function convert(xml: string, xslt: string) {
  try {
    // 检查输入是否为空
    if (!xml || !xslt) {
      return translate('tools.xslt.not-null');
    }

    // 创建 XML 和 XSLT 文档
    const parser = new DOMParser();
    const xmlDoc = parser.parseFromString(xml, 'text/xml');
    const xsltDoc = parser.parseFromString(xslt, 'text/xml');

    // 检查 XML 和 XSLT 是否有效
    if (xmlDoc.getElementsByTagName('parsererror').length > 0) {
      return translate('tools.xslt.error-xml');
    }
    if (xsltDoc.getElementsByTagName('parsererror').length > 0) {
      return translate('tools.xslt.error-xslt');
    }

    // 创建 XSLT 处理器并转换
    const processor = new XSLTProcessor();
    processor.importStylesheet(xsltDoc);
    const resultDoc = processor.transformToDocument(xmlDoc);

    // 获取转换结果
    const serializer = new XMLSerializer();
    return serializer.serializeToString(resultDoc);
  }
  catch (err) {
    return err instanceof Error ? err.message : err?.toString();
  }
}
