import xmlFormat from 'xml-formatter';
import { withDefaultOnError } from '@/utils/defaults';

export { prettifyXml, minifyXml, isValidXML };

function cleanRawXml(rawXml: string): string {
  return rawXml.trim();
}

function prettifyXml(rawXml: string, indentSize = 2): string {
  const options = {
    indentation: ' '.repeat(indentSize),
    collapseContent: true,
    lineSeparator: '\n',
  };
  return withDefaultOnError(() => xmlFormat(cleanRawXml(rawXml), options) ?? '', '');
}

function minifyXml(rawXml: string): string {
  return withDefaultOnError(() => xmlFormat.minify(cleanRawXml(rawXml), {}) ?? '', '');
}

function isValidXML(rawXml: string): boolean {
  const cleanedRawXml = cleanRawXml(rawXml);

  if (cleanedRawXml === '') {
    return true;
  }

  try {
    xmlFormat(cleanedRawXml);
    return true;
  }
  catch (e) {
    return false;
  }
}
