package cn.com.hiveview.launcherapi.module.portal.condition;

import java.io.Serializable;
import java.util.List;
import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class MergeVideoData extends BaseCondition implements Serializable{
	public Integer videoId;
	public String aqyVideoId;
	public String jqVideoId;
	public String hkdbVideoId;
	public String thirdVideoId;
	public Integer belongAlbumId;
	public String videoName;
	public Integer chnId;
	public String focus;
	public String duration;
	public String videoPic;
	public String phase;
	public Integer isEffective;
	public String videoDesc;
	public Integer seq;
	public String year;
	public Integer aqyIsEffective;
	public Integer jqIsEffective;
	public Integer thirdIsEffective;
	public Integer hkdbIsEffective;
	public Integer isHand;
	public Integer status;
	public String downTime;
	public String preM3u8;
	public String aqyId;
	//新加的
	public String cpName;//cp名称
	public String defination;//清晰度
	public String operateTime;//上下线操作时间
	public String cTime;//创建时间
	public String uTime;//更新时间
	public String albumId;
    public String albumName;
    public Integer sType;
  //新增
  	public String  epVid;//视频VID
	public String cName;//频道名称
	public Integer epType;//1 正片 2 片花
	public Integer epOrder;//视频的集数
	public Integer epLen;//视频的播放时长(秒)
	public Integer yearGeneration;//年代
	public String directors;
	public String writers;
	public String actors;//演员/MV 演员/嘉宾/配音角色
	public String mainActors;//主演/演唱者/主持人/配音
	public String guesters;//嘉宾
	public String songWriters;//作词
	public String producers;//出品人
	public String stars;//明星
	public String composers;//作曲
	public String makers;//制片人
	public String picUrl;//'剧集图片
	//爱奇艺新增vip字段
	private Integer epIsTvod; 	//默认的视频是否点播视频：0 非点播 1 点播
	private Integer epIsVip; 	//默认的视频是否会员视频：0 非会员 1 会员
	private Integer isTvod;  	//是否点播视频：0 非点播 1 点播
	private Integer epIsPkg; 	//默认的视频是否点播套餐视频：0 非点播套餐 1 点播套餐
	private Integer isVip;  	//是否会员视频：0 非会员 1 会员
	private Integer isCoupon; 	//是否点播券视频：0 非点播券 1 点播券
	private Integer isPkg;   	//是否点播套餐视频：0 非点播套餐 1 点播套餐
	private Integer epIsCoupon; //默认的视频是否点播券视频：0 非点播券 1 点播券
	private Integer epOrgPrc;   //视频的点播视频原价,当 epIsTvod 为 1 时才有这个字段，单位为分
	private String epValidTime;//视频的有效期,当 epIsTvod 为 1 时才有这个字段，取值:
									//如 24h、 30d
									//h：小时
									//d：天
									//M: 月
									//24h 为 24 小时
	private String drm;			//drm 属性，多个用逗号分隔；1:DRM_NONE 2:DRM_INTERTRUST 3:DRM_CHINA
	private String apkBagName;
	private String streams;
	private String sourceTypes;
	private Integer flag;
	public Integer getVideoId() {
		return videoId;
	}
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	public Integer getBelongAlbumId() {
		return belongAlbumId;
	}
	public void setBelongAlbumId(Integer belongAlbumId) {
		this.belongAlbumId = belongAlbumId;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public Integer getChnId() {
		return chnId;
	}
	public void setChnId(Integer chnId) {
		this.chnId = chnId;
	}
	public String getFocus() {
		return focus;
	}
	public void setFocus(String focus) {
		this.focus = focus;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getVideoPic() {
		return videoPic;
	}
	public void setVideoPic(String videoPic) {
		this.videoPic = videoPic;
	}
	public Integer getIsEffective() {
		return isEffective;
	}
	public void setIsEffective(Integer isEffective) {
		this.isEffective = isEffective;
	}
	public String getVideoDesc() {
		return videoDesc;
	}
	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getAqyVideoId() {
		return aqyVideoId;
	}
	public void setAqyVideoId(String aqyVideoId) {
		this.aqyVideoId = aqyVideoId;
	}
	public String getJqVideoId() {
		return jqVideoId;
	}
	public void setJqVideoId(String jqVideoId) {
		this.jqVideoId = jqVideoId;
	}
	public String getHkdbVideoId() {
		return hkdbVideoId;
	}
	public void setHkdbVideoId(String hkdbVideoId) {
		this.hkdbVideoId = hkdbVideoId;
	}
	public String getThirdVideoId() {
		return thirdVideoId;
	}
	public void setThirdVideoId(String thirdVideoId) {
		this.thirdVideoId = thirdVideoId;
	}
	public Integer getAqyIsEffective() {
		return aqyIsEffective;
	}
	public void setAqyIsEffective(Integer aqyIsEffective) {
		this.aqyIsEffective = aqyIsEffective;
	}
	public Integer getJqIsEffective() {
		return jqIsEffective;
	}
	public void setJqIsEffective(Integer jqIsEffective) {
		this.jqIsEffective = jqIsEffective;
	}
	public Integer getThirdIsEffective() {
		return thirdIsEffective;
	}
	public void setThirdIsEffective(Integer thirdIsEffective) {
		this.thirdIsEffective = thirdIsEffective;
	}
	public Integer getHkdbIsEffective() {
		return hkdbIsEffective;
	}
	public void setHkdbIsEffective(Integer hkdbIsEffective) {
		this.hkdbIsEffective = hkdbIsEffective;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public Integer getIsHand() {
		return isHand;
	}
	public void setIsHand(Integer isHand) {
		this.isHand = isHand;
	}
	public String getCpName() {
		return cpName;
	}
	public void setCpName(String cpName) {
		this.cpName = cpName;
	}
	public String getDefination() {
		return defination;
	}
	public void setDefination(String defination) {
		this.defination = defination;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public String getcTime() {
		return cTime;
	}
	public void setcTime(String cTime) {
		this.cTime = cTime;
	}
	public String getuTime() {
		return uTime;
	}
	public void setuTime(String uTime) {
		this.uTime = uTime;
	}
	public String getAlbumId() {
		return albumId;
	}
	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public Integer getsType() {
		return sType;
	}
	public void setsType(Integer sType) {
		this.sType = sType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDownTime() {
		return downTime;
	}
	public void setDownTime(String downTime) {
		this.downTime = downTime;
	}
	public String getPreM3u8() {
		return preM3u8;
	}
	public void setPreM3u8(String preM3u8) {
		this.preM3u8 = preM3u8;
	}
	public String getEpVid() {
		return epVid;
	}
	public void setEpVid(String epVid) {
		this.epVid = epVid;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public Integer getEpType() {
		return epType;
	}
	public void setEpType(Integer epType) {
		this.epType = epType;
	}
	public Integer getEpOrder() {
		return epOrder;
	}
	public void setEpOrder(Integer epOrder) {
		this.epOrder = epOrder;
	}
	public Integer getEpLen() {
		return epLen;
	}
	public void setEpLen(Integer epLen) {
		this.epLen = epLen;
	}
	public Integer getYearGeneration() {
		return yearGeneration;
	}
	public void setYearGeneration(Integer yearGeneration) {
		this.yearGeneration = yearGeneration;
	}
	public String getDirectors() {
		return directors;
	}
	public void setDirectors(String directors) {
		this.directors = directors;
	}
	public String getWriters() {
		return writers;
	}
	public void setWriters(String writers) {
		this.writers = writers;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getMainActors() {
		return mainActors;
	}
	public void setMainActors(String mainActors) {
		this.mainActors = mainActors;
	}
	public String getGuesters() {
		return guesters;
	}
	public void setGuesters(String guesters) {
		this.guesters = guesters;
	}
	public String getSongWriters() {
		return songWriters;
	}
	public void setSongWriters(String songWriters) {
		this.songWriters = songWriters;
	}
	public String getProducers() {
		return producers;
	}
	public void setProducers(String producers) {
		this.producers = producers;
	}
	public String getStars() {
		return stars;
	}
	public void setStars(String stars) {
		this.stars = stars;
	}
	public String getComposers() {
		return composers;
	}
	public void setComposers(String composers) {
		this.composers = composers;
	}
	public String getMakers() {
		return makers;
	}
	public void setMakers(String makers) {
		this.makers = makers;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getAqyId() {
		return aqyId;
	}
	public void setAqyId(String aqyId) {
		this.aqyId = aqyId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Integer getEpIsTvod() {
		return epIsTvod;
	}
	public void setEpIsTvod(Integer epIsTvod) {
		this.epIsTvod = epIsTvod;
	}
	public Integer getEpIsVip() {
		return epIsVip;
	}
	public void setEpIsVip(Integer epIsVip) {
		this.epIsVip = epIsVip;
	}
	public Integer getIsTvod() {
		return isTvod;
	}
	public void setIsTvod(Integer isTvod) {
		this.isTvod = isTvod;
	}
	public Integer getEpIsPkg() {
		return epIsPkg;
	}
	public void setEpIsPkg(Integer epIsPkg) {
		this.epIsPkg = epIsPkg;
	}
	public Integer getIsVip() {
		return isVip;
	}
	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}
	public Integer getIsCoupon() {
		return isCoupon;
	}
	public void setIsCoupon(Integer isCoupon) {
		this.isCoupon = isCoupon;
	}
	public Integer getIsPkg() {
		return isPkg;
	}
	public void setIsPkg(Integer isPkg) {
		this.isPkg = isPkg;
	}
	public Integer getEpIsCoupon() {
		return epIsCoupon;
	}
	public void setEpIsCoupon(Integer epIsCoupon) {
		this.epIsCoupon = epIsCoupon;
	}
	public Integer getEpOrgPrc() {
		return epOrgPrc;
	}
	public void setEpOrgPrc(Integer epOrgPrc) {
		this.epOrgPrc = epOrgPrc;
	}
	public String getEpValidTime() {
		return epValidTime;
	}
	public void setEpValidTime(String epValidTime) {
		this.epValidTime = epValidTime;
	}
	public String getDrm() {
		return drm;
	}
	public void setDrm(String drm) {
		this.drm = drm;
	}
	public String getApkBagName() {
		return apkBagName;
	}
	public void setApkBagName(String apkBagName) {
		this.apkBagName = apkBagName;
	}
	public String getStreams() {
		return streams;
	}
	public void setStreams(String streams) {
		this.streams = streams;
	}
	public String getSourceTypes() {
		return sourceTypes;
	}
	public void setSourceTypes(String sourceTypes) {
		this.sourceTypes = sourceTypes;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
}
