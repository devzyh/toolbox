import { test, expect } from '@playwright/test';

test.describe('Tool - Xml', () => {
  test.beforeEach(async ({ page }) => {
    await page.goto('/xml');
  });

  test('Has correct title', async ({ page }) => {
    await expect(page).toHaveTitle('Xml - DZ Tools');
  });

  test('', async ({ page }) => {

  });
});