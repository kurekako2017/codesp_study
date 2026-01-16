#!/bin/bash
# 数据备份脚本示例
pg_dump -U $DB_USER -h $DB_HOST $DB_NAME > backup_$(date +%F).sql
