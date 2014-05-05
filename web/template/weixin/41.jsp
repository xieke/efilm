<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="/WEB-INF/sand-html.tld"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="head.jsp" flush="false" />
<style>
.round li.other_select{position:relative}
.round li.other_select span{overflow:hidden;text-overflow:ellipsis;}
.round li.other_select span strong{white-space: nowrap; font-weight:normal}
.round li.other_select select{ left:0; top:0; right:0; bottom:0; position:absolute;filter:Alpha(Opacity=0);opacity:0;}
@-moz-document url-prefix(){ .round li.other_select select{opacity:.0001}}
</style>
<div class="cardexplain">



	<ul class="round">
		<li class="addr other_select"><span><strong>当前城市：上海</strong></span>
<select id="address">
<option>北京</option>
<option>上海</option>
<option>广州</option>
<option>深圳</option>
<option>天津</option>
<option>杭州</option>
</select>
        </li>
	</ul>
<script type="text/javascript">
$(function(){
	$('#address').change(function(){
		$('.other_select span strong').html("当前城市："+this.value);
	});
});
</script>
<style>
/*影院列表*/
	.S3{display:inline-block;height:20px;vertical-align:-6px;*vertical-align:-3px;padding-right:3px;background:url(${url}/images/cinemaico24.png)}
	.Imax{background-position: -120px -405px;width:44px;}
	.Lovers{background-position: -120px -469px;width:25px;}
	.Linecombo{background-position: -120px -381px;width:23px;}
	.Freepark{background-position: -120px -535px;width:22px;}
	.Child{background-position: -120px -499px;width:25px;}
</style>



    <ul class="round ul_text_img">
    	<li><a href="${url}/43.html">
        	<span style=" font-size:12px">
            <strong style="font-size:16px; color:#000; font-weight:bolder">上海和平影都旗舰店</strong> &nbsp;<strong class="txt_blue" style="color:#FF6600; font-size:22px">8.5</strong> &nbsp; <strong class="tags_blue" style="padding:2px 6px; color:#fff">团</strong> <strong class="tags_imax" style="padding:2px 6px; color:#fff">退</strong><br />
            [虹口区] 西江湾路388号龙之梦B座6F-05<br />
            电话：021-64250253 &nbsp;64250254<br />
            <div class="S3 Imax"></div>巨幕电影 
            <div class="S3 Lovers"></div>情侣座 
            <div class="S3 Freepark"></div>免费停车
            <br />
            最新活动：仅售79元！最高价值205元的双人观影套餐，可观看2D/3D！
            </span>
        </a></li>
    	<li><a href="${url}/43.html">
        	<span style=" font-size:12px">
            <strong style="font-size:16px; color:#000; font-weight:bolder">上海和平影都旗舰店</strong> &nbsp;<strong class="txt_blue" style="color:#FF6600; font-size:22px">7.2</strong> &nbsp; <strong class="tags_blue" style="padding:2px 6px; color:#fff">团</strong><br />
            [虹口区] 西江湾路388号龙之梦B座6F-05<br />
            电话：021-64250253 &nbsp;64250254<br />
            <div class="S3 Linecombo"></div>美食套餐 
            <div class="S3 Child"></div>婴儿服务
            </span>
        </a></li>
        <li><a href="${url}/43.html">
        	<span style=" font-size:12px">
            <strong style="font-size:16px; color:#000; font-weight:bolder">上海和平影都旗舰店</strong> &nbsp;<strong class="txt_blue" style="color:#FF6600; font-size:22px">7.8</strong> &nbsp; <strong class="tags_imax" style="padding:2px 6px; color:#fff">退</strong><br />
            [虹口区] 西江湾路388号龙之梦B座6F-05<br />
            电话：021-64250253 &nbsp;64250254<br />
            <div class="S3 Imax"></div>巨幕电影 
            <div class="S3 Lovers"></div>情侣座 
            <div class="S3 Freepark"></div>免费停车
            <br />
            最新活动：仅售79元！最高价值205元的双人观影套餐，可观看2D/3D！
            </span>
        </a></li>
        <li><a href="${url}/43.html">
        	<div class="txt">
            <strong>上海和平影都旗舰店</strong><br />
            地址：西藏中路2912312310号<br />
            口碑：<strong class="star_txt">★★★★☆ 4.8</strong>
            </div>
            <div class="img"><div class="nonsupport">不受理会员卡</div></div>
        </a></li>
    </ul>








</div>
<jsp:include page="foot.jsp" flush="false" />