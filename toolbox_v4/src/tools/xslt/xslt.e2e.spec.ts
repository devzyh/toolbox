import { test, expect } from '@playwright/test';

test.describe('Tool - Xslt', () => {
  test.beforeEach(async ({ page }) => {
    await page.goto('/xslt');
  });

  test('Has correct title', async ({ page }) => {
    await expect(page).toHaveTitle('Xslt - DZ Tools');
  });

  test('', async ({ page }) => {

  });
});