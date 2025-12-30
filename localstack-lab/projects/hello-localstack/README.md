# Hello LocalStack

Minimal example using LocalStack. The script creates an S3 bucket and uploads a test object using boto3 with LocalStack endpoints.

## Requirements
- LocalStack running locally (e.g., `localstack start` or Docker). Default endpoints: http://localhost:4566
- Python 3.9+ and boto3 installed (bootstrap script sets up venv and installs requirements)

## Quick run

```bash
cd /workspaces/study/localstack-lab
./scripts/bootstrap.sh
source .venv/bin/activate
# ensure LocalStack is running before this step
python projects/hello-localstack/main.py
```

## Environment
- Uses `LOCALSTACK_ENDPOINT_URL` if set; otherwise defaults to `http://localhost:4566`.
- Bucket name: `hello-localstack-bucket`
- Object key: `hello.txt`
