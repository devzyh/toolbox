import requests
from pathlib import Path

# cdnjs库名称及版本
target = "crypto-js/4.2.0"


def download_file(url, destination_folder):
    """下载单个文件"""
    # 获取文件名
    file_name = url.split('/')[-1]
    # 创建完整的文件路径
    file_path = destination_folder / file_name
    # 发送GET请求
    response = requests.get("https://cdnjs.cloudflare.com/ajax/libs/" + target + "/" + url)
    # 如果请求成功
    if response.status_code == 200:
        # 写入文件
        with open(file_path, 'wb') as file:
            file.write(response.content)
        print(f"Downloaded {file_name}")
    else:
        print(f"Failed to download {file_name}")


def main():
    # API URL
    api_url = 'https://api.cdnjs.com/libraries/' + target
    # 发送请求获取JSON数据
    response = requests.get(api_url)
    data = response.json()

    # 获取rawFiles列表
    raw_files = data.get('rawFiles', [])

    # 遍历每个文件URL
    for file_url in raw_files:
        # 提取文件路径中的目录部分
        path_parts = (target + "/" + file_url).split('/')
        # 去除空字符串和文件名
        directory_parts = [part for part in path_parts[:-1] if part]
        # 创建目录
        directory = Path(*directory_parts)
        directory.mkdir(parents=True, exist_ok=True)

        # 下载文件
        download_file(file_url, directory)


if __name__ == '__main__':
    main()
