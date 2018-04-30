import sys
import svn.remote
import shutil
import os
import threading

def refresh():
	print "Updating INI!"
	if os.path.exists("temp"):
		shutil.rmtree('temp');
	r = svn.remote.RemoteClient('http://vcs.gentex.com/svn/testers/users/nate.hansen/PartVerifier/res/PartNumbers')
	r.checkout('temp')
	threading.Timer(900.0, refresh).start()

if __name__ == '__main__':
    cmd = sys.argv[1]
    #username = sys.argv[2]
    #passw = sys.argv[3]

    if cmd == 'checkout' or cmd == 'get':
        refresh()
    elif cmd == 'commit':
        r = 5;