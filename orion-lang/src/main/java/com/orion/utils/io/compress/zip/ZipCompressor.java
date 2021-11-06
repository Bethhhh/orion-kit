package com.orion.utils.io.compress.zip;

import com.orion.constant.Const;
import com.orion.utils.io.Files1;
import com.orion.utils.io.Streams;
import com.orion.utils.io.compress.BaseFileCompressor;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * zip压缩器
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2021/9/27 16:37
 */
public class ZipCompressor extends BaseFileCompressor {

    public ZipCompressor() {
        this(Const.SUFFIX_ZIP);
    }

    public ZipCompressor(String suffix) {
        super(suffix);
    }

    @Override
    public void doCompress() throws Exception {
        try (ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(Files1.openOutputStreamFast(this.getAbsoluteCompressPath())))) {
            // 设置压缩文件
            for (Map.Entry<String, File> fileEntity : compressFiles.entrySet()) {
                try (InputStream in = Files1.openInputStreamFast(fileEntity.getValue())) {
                    out.putNextEntry(new ZipEntry(fileEntity.getKey()));
                    Streams.transfer(in, out);
                    out.closeEntry();
                }
            }
            for (Map.Entry<String, InputStream> fileEntity : compressStreams.entrySet()) {
                out.putNextEntry(new ZipEntry(fileEntity.getKey()));
                Streams.transfer(fileEntity.getValue(), out);
                out.closeEntry();
            }
            out.finish();
        }
    }

}