import argparse
import requests
import os.path, os, pathlib

parser = argparse.ArgumentParser()

parser.add_argument(
    "-c",
    "--checkstyle",
    help="Path of the checkstyle file",
    action="store",
    required=True,
    type=str,
)

parser.add_argument(
    "-f",
    "--file",
    help="Path of the file to be checkstyled",
    action="store",
    required=True,
    type=str,
)

parser.add_argument(
    "--jar", help="Path to checkstyle jar", action="store", required=False, type=str
)

args = parser.parse_args()


def download_file(url):
    local_filename = url.split("/")[-1]
    # NOTE the stream=True parameter below
    with requests.get(url, stream=True) as r:
        r.raise_for_status()
        with open(local_filename, "wb") as f:
            for chunk in r.iter_content(chunk_size=8192):
                # If you have chunk encoded response uncomment if
                # and set chunk_size parameter to None.
                # if chunk:
                f.write(chunk)
    return local_filename


jar = ""
if args.jar == None:
    print("Downloading latest checkstyle")
    r = requests.get(
        "https://api.github.com/repos/checkstyle/checkstyle/releases/latest",
        headers={
            "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36",
        },
    ).json()
    name = r["assets"][0]["name"]
    if not os.path.exists("./" + name):
        print(r["assets"][0]["browser_download_url"])
        download_file(r["assets"][0]["browser_download_url"])
        jar = name
    else:
        print("Using " + name)
        jar = name
else:
    jar = args.jar

checkstyle = str(pathlib.Path(args.checkstyle).absolute())
javaFile = str(pathlib.Path(args.file).absolute())
jarPath = str(pathlib.Path(jar).absolute())

os.system("java -jar %s -c %s %s" % (jarPath, checkstyle, javaFile))

