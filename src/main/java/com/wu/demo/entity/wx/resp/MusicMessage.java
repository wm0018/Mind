package com.wu.demo.entity.wx.resp;

/**
 * 音乐消息
 * @author Wu
 *
 */
public class MusicMessage extends RespBaseMessage{
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}

}
