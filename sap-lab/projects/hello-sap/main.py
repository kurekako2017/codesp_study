"""SAP 练习入门示例。

将此占位逻辑替换为 SAP SDK 示例、API 调用或模拟集成。
"""

from datetime import datetime


def main() -> None:
    now = datetime.utcnow().isoformat(timespec="seconds") + "Z"
    print(f"你好，SAP 实验！当前时间（UTC）：{now}")
    print("下一步：在此添加真实的 SAP SDK 调用或 OData 请求。")


if __name__ == "__main__":
    main()
