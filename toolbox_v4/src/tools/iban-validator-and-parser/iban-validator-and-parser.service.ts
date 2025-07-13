import { ValidationErrorsIBAN } from 'ibantools';
import { translate } from '@/plugins/i18n.plugin';

const ibanErrorToMessage = {
  [ValidationErrorsIBAN.NoIBANProvided]: translate('tools.iban-validator-and-parser.no-iban-provided'),
  [ValidationErrorsIBAN.NoIBANCountry]: translate('tools.iban-validator-and-parser.no-iban-country'),
  [ValidationErrorsIBAN.WrongBBANLength]: translate('tools.iban-validator-and-parser.wrong-bban-length'),
  [ValidationErrorsIBAN.WrongBBANFormat]: translate('tools.iban-validator-and-parser.wrong-bban-format'),
  [ValidationErrorsIBAN.ChecksumNotNumber]: translate('tools.iban-validator-and-parser.checksum-not-number'),
  [ValidationErrorsIBAN.WrongIBANChecksum]: translate('tools.iban-validator-and-parser.wrong-iban-checksum'),
  [ValidationErrorsIBAN.WrongAccountBankBranchChecksum]: translate('tools.iban-validator-and-parser.wrong-account-bank-branch-checksum'),
  [ValidationErrorsIBAN.QRIBANNotAllowed]: translate('tools.iban-validator-and-parser.qr-iban-not-allowed'),
};

export function getFriendlyErrors(errorCodes: ValidationErrorsIBAN[]) {
  return errorCodes.map(errorCode => ibanErrorToMessage[errorCode]).filter(Boolean);
}
