#!/usr/bin/env bash
set -euo pipefail

# 为 SAP 实验引导并创建隔离的 Python 虚拟环境。
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
LAB_ROOT="${SCRIPT_DIR}/.."
cd "${LAB_ROOT}"

if [ ! -d .venv ]; then
  python3 -m venv .venv
fi

# shellcheck disable=SC1091
source .venv/bin/activate
python -m pip install --upgrade pip

REQ_FILE="${LAB_ROOT}/projects/hello-sap/requirements.txt"
if [ -f "${REQ_FILE}" ]; then
  pip install -r "${REQ_FILE}"
fi

echo "SAP 实验环境已准备好。激活请运行：source ${LAB_ROOT}/.venv/bin/activate"
