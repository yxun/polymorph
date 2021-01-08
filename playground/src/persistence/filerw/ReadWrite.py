import os

class FileRW:
""" FileRW an instance of file reading and writing methods.
    self.fileRead: file path and name for reading
    self.fileWrite: file path and name for writing

"""

    def __init__(self, fileRead, fileWrite):
        self.fileRead = fileRead
        self.fileWrite = fileWrite
        self.readMode = 'r'
        self.writeMode = 'w'

    def Read(self, debug):
        with open(self.fileRead, self.readMode) as f:
            content = f.read()
            if debug:
                print(content)

    def Write(self, content):
        with open(self.fileWrite, self.fileWrite) as f:
            f.write(content)
            f.flush()

    def Create(self):
        with open(self.fileWrite, 'x') as f:
            f.close()

    def Delete(self):
        os.remove(self.fileWrite)

    def isFile(self):
        return os.path.isFile(self.fileRead)
