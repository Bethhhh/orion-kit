package com.orion.remote.connection.sftp.bigfile;

import ch.ethz.ssh2.SFTPv3FileHandle;
import com.orion.constant.Const;
import com.orion.remote.connection.sftp.SftpExecutor;
import com.orion.remote.connection.sftp.SftpFile;
import com.orion.support.download.BaseFileDownload;
import com.orion.utils.Exceptions;
import com.orion.utils.Valid;

import java.io.File;
import java.io.IOException;

/**
 * SFTP 大文件下载 支持断点续传, 实时速率
 *
 * @author ljh15
 * @version 1.0.0
 * @since 2020/5/14 14:34
 */
public class SftpDownload extends BaseFileDownload {

    private static final String LOCK_SUFFIX = "orion.sftp.download";

    /**
     * 实例
     */
    private SftpExecutor executor;

    /**
     * 文件处理器
     */
    private SFTPv3FileHandle handle;

    /**
     * 当前位置
     */
    private long current;

    public SftpDownload(SftpExecutor executor, String remote, String local) {
        this(executor, remote, new File(local));
    }

    public SftpDownload(SftpExecutor executor, String remote, File local) {
        super(remote, local, LOCK_SUFFIX, Const.BUFFER_KB_32);
        Valid.notNull(executor, "sftp executor is null");
        this.executor = executor;
    }

    @Override
    public void run() {
        try {
            super.startDownload();
        } catch (IOException e) {
            throw Exceptions.sftp("sftp download exception remote file: " + remote + " -> local file: " + local.getAbsolutePath(), e);
        }
    }

    @Override
    protected long getFileSize() {
        SftpFile remoteFile = executor.getFile(remote);
        if (remoteFile == null) {
            throw Exceptions.notFound("not found download remote file");
        }
        return remoteFile.getSize();
    }

    @Override
    protected void initDownload(boolean breakPoint, long skip) {
        current += skip;
        handle = executor.openFileHandler(remote, 1);
        if (handle == null) {
            throw Exceptions.sftp("unopened remote file stream");
        }
    }

    @Override
    protected int read(byte[] bs) throws IOException {
        int r = executor.read(handle, current, bs);
        if (r != -1) {
            current += r;
        }
        return r;
    }

    @Override
    protected void transferFinish() {
        if (handle != null) {
            executor.closeFile(handle);
        }
    }

}
