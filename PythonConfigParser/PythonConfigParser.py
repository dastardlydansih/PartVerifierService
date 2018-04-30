import sys
import configparser
import os

#oldPath = 'C:/Projects/REST/pythonIniScript/PythonConfigParser/PythonConfigParser/temp/PartVerifierPartNumbers.ini'

class MyConfigParser(configparser.RawConfigParser):
    def write(self, fp):
        """Write an .ini-format representation of the configuration state."""
        if self._defaults:
            fp.write("[%s]\n" % configparser.DEFAULTSECT)
            for (key, value) in self._defaults.items():
                fp.write("%s=%s\n" % (key, str(value).replace('\n', '\n\t')))
            fp.write("\n")
        for section in self._sections:
            fp.write("[%s]\n" % section)
            for (key, value) in self._sections[section].items():
                if key == "__name__":
                    continue
                if (value is not None) or (self._optcre == self.OPTCRE):
                    key = "=".join((key, str(value).replace('\n', '\n\t')))
                fp.write("%s\n" % key)
            fp.write("\n")

Config = MyConfigParser()
Config.optionxform = str
Config.read('temp/PartVerifierPartNumbers.ini')
Config.sections()

def writeToIni(cmd, group, key, value):
    try:
        Config.set(group,key,value)
    except configparser.NoSectionError:
        print("ERROR: Config.NoSectionError. " + group + " does not exist.")
        return;
    with open('temp/PartVerifierPartNumbers.ini', 'wb') as configfile:    # save
        Config.write(configfile)
        result = Config[group][key]
        print(result)

def addToIni(cmd, group, key, value):
    if Config.has_section(group):
        if Config.has_option(group, key):
            if value != 'null' and value != '""':
                writeToIni(cmd, group, key, value)
                return
        elif key != 'null' and key != '':
            writeToIni(cmd, group, key, value)
            return
    else:
        Config.add_section(group)
        if key != 'null':
            Config.set(group,key,value)
    
    with open('temp/PartVerifierPartNumbers.ini', 'wb') as configfile:    # save
        Config.write(configfile)
    result = Config[group][key]
    print(result)

if __name__ == '__main__':
    cmd = sys.argv[1]
    group = sys.argv[2]
    key = sys.argv[3]

    if cmd == 'read' or cmd == 'get':
        result = Config[group][key]
        print(result)
    elif cmd == 'write' or cmd == 'set':
        value = sys.argv[4]
        writeToIni(cmd, group, key, value)
    elif cmd == 'add' or cmd == 'create':
        value = sys.argv[4]
        addToIni(cmd, group, key, value)
        

