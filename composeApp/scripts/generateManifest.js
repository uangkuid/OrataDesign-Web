#!/usr/bin/env node
/**
 * Script to generate manifest.json for templates directory.
 * This script scans all subdirectories in the templates folder and creates a JSON manifest
 * listing all files within each subdirectory.
 * 
 * @author oratakashi
 * @since 14 Jan 2026
 */

const fs = require('fs');
const path = require('path');

// Get the composeApp directory
const scriptDir = __dirname;
const composeAppDir = path.dirname(scriptDir);

const templatesDir = path.join(composeAppDir, 'src/commonMain/kotlin/com/oratakashi/design/docs/ui/templates');
const outputFile = path.join(composeAppDir, 'src/commonMain/composeResources/files/template/manifest.json');

// Create output directory if it doesn't exist
const outputDir = path.dirname(outputFile);
if (!fs.existsSync(outputDir)) {
  fs.mkdirSync(outputDir, { recursive: true });
}

// Build manifest array
const manifest = [];

// Iterate through subdirectories
if (fs.existsSync(templatesDir)) {
  const items = fs.readdirSync(templatesDir, { withFileTypes: true })
    .filter(item => item.isDirectory())
    .map(item => item.name)
    .sort();
  
  for (const itemName of items) {
    const itemPath = path.join(templatesDir, itemName);
    const files = fs.readdirSync(itemPath, { withFileTypes: true })
      .filter(f => f.isFile())
      .map(f => f.name)
      .sort();
    
    manifest.push({
      name: itemName,
      content: files
    });
  }
}

// Write JSON with proper formatting
fs.writeFileSync(outputFile, JSON.stringify(manifest, null, 2) + '\n');

const relativeOutput = path.relative(composeAppDir, outputFile);
console.log(`✓ Generated manifest.json at: ${relativeOutput}`);
console.log(`✓ Manifest contains ${manifest.length} template directories`);
