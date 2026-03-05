#!/usr/bin/env python3
from pathlib import Path
from collections import defaultdict

ROOT = Path(__file__).resolve().parents[1]
SRC = ROOT / "src" / "main" / "java"

PATTERNS = {
    "NeoForge API (1.7.10 不存在)": "net.neoforged",
    "Data Components (1.20+ 特性)": "DataComponents",
    "Attachment System (NeoForge 专属)": "Attachment",
    "Modern serialization codec": "StreamCodec",
    "Java 21 pattern matching / modern APIs": "instanceof",
}

hits = defaultdict(list)
for java in SRC.rglob("*.java"):
    text = java.read_text(encoding="utf-8")
    lines = text.splitlines()
    for idx, line in enumerate(lines, start=1):
        for name, key in PATTERNS.items():
            if key in line:
                hits[name].append((java.relative_to(ROOT).as_posix(), idx, line.strip()))

report = ["# 1.7.10 兼容性审计报告", "", "此报告由 `tools/audit_legacy_compat.py` 自动生成。", ""]
for name in PATTERNS:
    items = hits.get(name, [])
    report.append(f"## {name}")
    report.append(f"- 命中数: **{len(items)}**")
    for file, line, content in items[:20]:
        report.append(f"- `{file}:{line}` -> `{content}`")
    if len(items) > 20:
        report.append(f"- ... 其余 {len(items)-20} 条省略")
    report.append("")

out = ROOT / "docs" / "legacy_1_7_10_audit.md"
out.write_text("\n".join(report) + "\n", encoding="utf-8")
print(f"Wrote {out.relative_to(ROOT)}")
