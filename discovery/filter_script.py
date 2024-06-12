script = '''
def callback(commit, metadata):
    if commit.author_email == b"labrasibrahim@gmail.com":
        commit.message = b""
        commit.author_name = b""
        commit.author_email = b""
'''
