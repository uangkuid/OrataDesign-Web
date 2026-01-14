#!/usr/bin/env python3
"""
Script to generate manifest.json for templates directory.
This script scans all subdirectories in the templates folder and creates a JSON manifest
listing all files within each subdirectory.

@author oratakashi
@since 14 Jan 2026
"""

import os
import json
from pathlib import Path

# Get the composeApp directory
script_dir = Path(__file__).parent
compose_app_dir = script_dir.parent

templates_dir = compose_app_dir / "src/commonMain/kotlin/com/oratakashi/design/docs/ui/templates"
output_file = compose_app_dir / "src/commonMain/composeResources/files/template/manifest.json"

# Create output directory if it doesn't exist
output_file.parent.mkdir(parents=True, exist_ok=True)

manifest = {}

# Iterate through subdirectories
if templates_dir.exists():
    for item in sorted(templates_dir.iterdir()):
        if item.is_dir():
            files = sorted([f.name for f in item.iterdir() if f.is_file()])
            manifest[item.name] = files

# Write JSON with proper formatting
with open(output_file, 'w') as f:
    json.dump(manifest, f, indent=2)

print(f"✓ Generated manifest.json at: {output_file.relative_to(compose_app_dir)}")
print(f"✓ Manifest contains {len(manifest)} template directories")
