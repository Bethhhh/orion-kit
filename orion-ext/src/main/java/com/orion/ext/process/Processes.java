package com.orion.ext.process;

import com.orion.lang.support.Attempt;
import com.orion.lang.utils.io.Streams;

import java.io.ByteArrayOutputStream;

/**
 * process runtime 工具
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2021/3/31 9:46
 */
public class Processes {

    private Processes() {
    }

    public static String getOutputResultString(String... command) {
        return new String(getOutputResult(false, command));
    }

    public static String getOutputResultString(boolean redirectError, String... command) {
        return new String(getOutputResult(redirectError, command));
    }

    public static byte[] getOutputResult(String... command) {
        return getOutputResult(false, command);
    }

    /**
     * 获取输出结果
     * <p>
     * 适用于不阻塞结果的命令 如: echo
     * 可能会抛出运行时异常
     *
     * @param redirectError 合并错误流到输出流
     * @param command       command
     * @return result
     */
    public static byte[] getOutputResult(boolean redirectError, String... command) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ProcessAwaitExecutor executor = new ProcessAwaitExecutor(command);
        try {
            if (redirectError) {
                executor.redirectError();
            }
            executor.streamHandler((p, i) -> Attempt.uncheck(Streams::transfer, i, out))
                    .waitFor()
                    .sync()
                    .terminal()
                    .exec();
            return out.toByteArray();
        } finally {
            Streams.close(out);
            Streams.close(executor);
        }
    }

    public static String getOutputResultWithDirString(String dir, String... command) {
        return new String(getOutputResultWithDir(false, dir, command));
    }

    public static String getOutputResultWithDirString(boolean redirectError, String dir, String... command) {
        return new String(getOutputResultWithDir(redirectError, dir, command));
    }

    public static byte[] getOutputResultWithDir(String dir, String... command) {
        return getOutputResultWithDir(false, dir, command);
    }

    /**
     * 获取输出结果
     * <p>
     * 适用于不阻塞结果的命令
     *
     * @param redirectError 合并错误流到输出流
     * @param dir           执行文件夹
     * @param command       command
     * @return result
     */
    public static byte[] getOutputResultWithDir(boolean redirectError, String dir, String... command) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ProcessAwaitExecutor executor = new ProcessAwaitExecutor(command);
        try {
            if (redirectError) {
                executor.redirectError();
            }
            executor.streamHandler((p, i) -> Attempt.uncheck(Streams::transfer, i, out))
                    .waitFor()
                    .sync()
                    .dir(dir)
                    .exec();
            return out.toByteArray();
        } finally {
            Streams.close(out);
            Streams.close(executor);
        }
    }

}
