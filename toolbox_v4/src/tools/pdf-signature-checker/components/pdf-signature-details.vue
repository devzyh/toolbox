<script setup lang="ts">
import { computed, toRefs } from 'vue';
import type { SignatureInfo } from '../pdf-signature-checker.types';
import { translate } from '@/plugins/i18n.plugin';

const props = defineProps<{ signature: SignatureInfo }>();
const { signature } = toRefs(props);

const tableHeaders = {
  validityPeriod: translate('tools.pdf-signature-checker.validity-period'),
  issuedBy: translate('tools.pdf-signature-checker.issued-by'),
  issuedTo: translate('tools.pdf-signature-checker.issued-to'),
  pemCertificate: translate('tools.pdf-signature-checker.pem-certificate'),
};

const certs = computed(() => signature.value.meta.certs.map((certificate, index) => ({
  ...certificate,
  validityPeriod: {
    notBefore: new Date(certificate.validityPeriod.notBefore).toLocaleString(),
    notAfter: new Date(certificate.validityPeriod.notAfter).toLocaleString(),
  },
  certificateName: translate('tools.pdf-signature-checker.signature-certificates', { index: index + 1 }),
})));
</script>

<template>
  <div flex flex-col gap-2>
    <c-table :data="certs" :headers="tableHeaders">
      <template #validityPeriod="{ value }">
        <c-key-value-list
          :items="[{
            label: translate('tools.pdf-signature-checker.not-before'),
            value: value.notBefore,
          }, {
            label: translate('tools.pdf-signature-checker.not-after'),
            value: value.notAfter,
          }]"
        />
      </template>

      <template #issuedBy="{ value }">
        <c-key-value-list
          :items="[{
            label: translate('tools.pdf-signature-checker.common-name'),
            value: value.commonName,
          }, {
            label: translate('tools.pdf-signature-checker.organization-name'),
            value: value.organizationName,
          }, {
            label: translate('tools.pdf-signature-checker.country-name'),
            value: value.countryName,
          }, {
            label: translate('tools.pdf-signature-checker.locality-name'),
            value: value.localityName,
          }, {
            label: translate('tools.pdf-signature-checker.organizational-unit-name'),
            value: value.organizationalUnitName,
          }, {
            label: translate('tools.pdf-signature-checker.state-or-province-name'),
            value: value.stateOrProvinceName,
          }]"
        />
      </template>

      <template #issuedTo="{ value }">
        <c-key-value-list
          :items="[{
            label: translate('tools.pdf-signature-checker.common-name'),
            value: value.commonName,
          }, {
            label: translate('tools.pdf-signature-checker.organization-name'),
            value: value.organizationName,
          }, {
            label: translate('tools.pdf-signature-checker.country-name'),
            value: value.countryName,
          }, {
            label: translate('tools.pdf-signature-checker.locality-name'),
            value: value.localityName,
          }, {
            label: translate('tools.pdf-signature-checker.organizational-unit-name'),
            value: value.organizationalUnitName,
          }, {
            label: translate('tools.pdf-signature-checker.state-or-province-name'),
            value: value.stateOrProvinceName,
          }]"
        />
      </template>

      <template #pemCertificate="{ value }">
        <c-modal-value :value="value" :label="translate('tools.pdf-signature-checker.view-pem-cert')">
          <template #value>
            <div break-all text-xs>
              {{ value }}
            </div>
          </template>
        </c-modal-value>
      </template>
    </c-table>
  </div>
</template>
