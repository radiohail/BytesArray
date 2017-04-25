import gzip, binascii, os
from cStringIO import StringIO

def gzip_compress(raw_data):
    buf = StringIO()
    f = gzip.GzipFile(mode='wb', fileobj=buf)
    try:
        f.write(raw_data)
    finally:
        f.close()
    return buf.getvalue()

def gzip_uncompress(c_data):
    buf = StringIO(c_data)
    f = gzip.GzipFile(mode = 'rb', fileobj = buf)
    try:
        r_data = f.read()
    finally:
        f.close()
    return r_data


def compress_file(fn_in, fn_out):
    f_in = open(fn_in, 'rb')
    f_out = gzip.open(fn_out, 'wb')
    f_out.writelines(f_in)
    f_out.close()
    f_in.close()

def uncompress_file(fn_in, fn_out):
    f_in = gzip.open(fn_in, 'rb')
    f_out = open(fn_out, 'wb')
    file_content = f_in.read()
    f_out.write(file_content)
    f_out.close()
    f_in.close()


if __name__ == '__main__':
    in_data = ''
    for i in range(0,100):
        in_data += "00000000111222233334455"
    
    # in_data = chr(0)*76800
    print in_data
    print len(in_data)
    out_data = gzip_compress(in_data)
    print out_data # binascii.hexlify(out_data)
    print len(out_data)

    r_data = gzip_uncompress(out_data)
    print r_data
