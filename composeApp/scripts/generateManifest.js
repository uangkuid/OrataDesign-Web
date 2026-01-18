#!/usr/bin/env node
/**
 * Script to generate manifest.json for templates directory.
 * This script scans all subdirectories in the templates folder and creates a JSON manifest
 * listing all files within each subdirectory, including file path, extension, and type.
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
const composeResourcesDir = path.join(composeAppDir, 'src/commonMain/composeResources/files/templates');
const outputFile = path.join(composeAppDir, 'src/commonMain/composeResources/files/templates/manifest.json');

// Create output directory if it doesn't exist
const outputDir = path.dirname(outputFile);
if (!fs.existsSync(outputDir)) {
  fs.mkdirSync(outputDir, { recursive: true });
}

// Helper to determine file type by extension
function getFileType(extension) {
  switch (extension.toLowerCase()) {
    case '.kt':
      return 'Kotlin';
    case '.xml':
      return 'Xml';
    default:
      return 'Unknown';
  }
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
    const itemContents = fs.readdirSync(itemPath, { withFileTypes: true });

    // Get direct files in the template folder
    const directFiles = itemContents
      .filter(f => f.isFile())
      .map(f => {
        const fileName = f.name;
        const ext = path.extname(fileName);
        const type = getFileType(ext);
        const relPath = path.join('files/templates', itemName, fileName);
        return {
          name: fileName,
          filepath: relPath,
          extension: ext,
          fileType: type
        };
      })
      .sort((a, b) => a.name.localeCompare(b.name));

    // Get subdirectories (variants) - check if there's a "variant" subfolder
    let variants = [];
    const variantFolder = itemContents.find(item => item.isDirectory() && item.name === 'variant');

    if (variantFolder) {
      const variantPath = path.join(itemPath, 'variant');
      const variantSubdirs = fs.readdirSync(variantPath, { withFileTypes: true })
        .filter(item => item.isDirectory());

      variants = variantSubdirs.map(subDir => {
        const subDirName = subDir.name;
        const subDirPath = path.join(variantPath, subDirName);

        // Read only files in the variant folder (ignore deeper subdirectories)
        const variantFiles = fs.readdirSync(subDirPath, { withFileTypes: true })
          .filter(f => f.isFile())
          .map(f => {
            const fileName = f.name;
            const ext = path.extname(fileName);
            const type = getFileType(ext);
            const relPath = path.join('files/templates', itemName, 'variant', subDirName, fileName);
            return {
              name: fileName,
              filepath: relPath,
              extension: ext,
              fileType: type
            };
          })
          .sort((a, b) => a.name.localeCompare(b.name));

        return {
          name: subDirName,
          content: variantFiles
        };
      })
      .sort((a, b) => a.name.localeCompare(b.name));
    }

    const templateEntry = {
      name: itemName
    };

    // Add variants array if there are any subdirectories
    if (variants.length > 0) {
      templateEntry.variant = variants;
    }

    // Add content array if there are any direct files
    if (directFiles.length > 0) {
      templateEntry.content = directFiles;
    }

    manifest.push(templateEntry);
  }

  console.log('Manifest: ' + JSON.stringify(manifest, null, 2));
}

// Write JSON with proper formatting
fs.writeFileSync(outputFile, JSON.stringify(manifest, null, 2) + '\n');

const relativeOutput = path.relative(composeAppDir, outputFile);
console.log(`✓ Generated manifest.json at: ${relativeOutput}`);
console.log(`✓ Manifest contains ${manifest.length} template directories`);
