package cn.com.hiveview.entity.module.portal;

public class MergeVideoSourceDataVo {
	
	  public Integer sourceId;
	  public Integer belongVideoId;
	  public String playAddress;
	  public String playFormat;//mp4 或者是 m3u8
	  public Integer sourceType;//来源方
	  public Integer area;
	  public String definition;//分辨率
	  public String stream;//码流
	  public String crEndDate;//版权结束时间
	  public Integer isEffective;
	  public Integer ysIsEffective;
	  public Integer isHand;
	  public String partnerId;
	  public String videoName;
	  public Integer belongAlbumId;
	  public String streams;
	  public String sourceTypes;
	public Integer getSourceId() {
		return sourceId;
	}
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	public Integer getBelongVideoId() {
		return belongVideoId;
	}
	public void setBelongVideoId(Integer belongVideoId) {
		this.belongVideoId = belongVideoId;
	}
	public String getPlayAddress() {
		return playAddress;
	}
	public void setPlayAddress(String playAddress) {
		this.playAddress = playAddress;
	}
	public String getPlayFormat() {
		return playFormat;
	}
	public void setPlayFormat(String playFormat) {
		this.playFormat = playFormat;
	}
	public Integer getSourceType() {
		return sourceType;
	}
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	
	public String getCrEndDate() {
		return crEndDate;
	}
	public void setCrEndDate(String crEndDate) {
		this.crEndDate = crEndDate;
	}
	public Integer getIsEffective() {
		return isEffective;
	}
	public void setIsEffective(Integer isEffective) {
		this.isEffective = isEffective;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public Integer getIsHand() {
		return isHand;
	}
	public void setIsHand(Integer isHand) {
		this.isHand = isHand;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public Integer getBelongAlbumId() {
		return belongAlbumId;
	}
	public void setBelongAlbumId(Integer belongAlbumId) {
		this.belongAlbumId = belongAlbumId;
	}
	public Integer getYsIsEffective() {
		return ysIsEffective;
	}
	public void setYsIsEffective(Integer ysIsEffective) {
		this.ysIsEffective = ysIsEffective;
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
}
