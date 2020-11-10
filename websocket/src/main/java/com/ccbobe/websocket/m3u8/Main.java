package com.ccbobe.websocket.m3u8;

public class Main {

    public static void main(String[] args) {
        String originUrlpath = "https://2.ddyunbo.com/20191229/N3qcOpKv/index.m3u8";
        String preUrlPath = originUrlpath.substring(0, originUrlpath.lastIndexOf("/")+1);
        String rootPath = "D:\\videodir";
        String fileName = "video.mp4";
        HlsDownloader downLoader = new HlsDownloader(originUrlpath, preUrlPath, rootPath);
        downLoader.setThreadQuantity(10);
        try{
            fileName = downLoader.download(true);
        }
        catch (Exception e) {

        }

        if(fileName.isEmpty()){
            System.out.println("下载失败");
        }else{
            System.err.println("下载成功");
        }
    }
}
