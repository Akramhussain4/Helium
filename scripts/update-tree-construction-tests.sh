#!/usr/bin/env bash
# Copies the tree construction tests (.dat files) from web-platform-tests at a given commit into the test
# resources. They moved there from html5lib-tests in June 2026; the repository is too large for a submodule.
#
# Usage: scripts/update-tree-construction-tests.sh <wpt-commit-sha>
set -euo pipefail

sha="${1:?Usage: $0 <wpt-commit-sha>}"
source_dir="html/syntax/parsing/resources"
target_dir="$(cd "$(dirname "$0")/.." && pwd)/html-parser/src/commonTest/resources/tree-construction"

files=$(curl -fsSL "https://api.github.com/repos/web-platform-tests/wpt/contents/$source_dir?ref=$sha" \
    | grep -o '"name": *"[^"]*\.dat"' | sed 's/.*"\([^"]*\.dat\)"/\1/')

rm -rf "$target_dir"
mkdir -p "$target_dir"
for file in $files; do
    curl -fsSL -o "$target_dir/$file" "https://raw.githubusercontent.com/web-platform-tests/wpt/$sha/$source_dir/$file"
done
echo "$sha" > "$target_dir/WPT_COMMIT"
echo "Copied $(echo "$files" | wc -l | tr -d ' ') files from web-platform-tests@$sha"
