from pathlib import Path

def getData(dayNum):
    mod_path = Path(__file__).parent
    relative_path = '../data/day' + str(dayNum) + '.txt'
    src_path = (mod_path / relative_path).resolve()

    file = open(src_path, "r")
    return file.read().splitlines()