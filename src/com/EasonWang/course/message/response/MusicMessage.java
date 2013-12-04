package com.EasonWang.course.message.response;

/**
 * 响应消息--音乐消息
 * @author EasonWang
 * @date 2013年10月31日
 */
public class MusicMessage extends BaseMessage {
	// 音乐  
    private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
    
    
}
