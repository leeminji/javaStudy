<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/header.jsp"></c:import>
<!-- content -->
<section class="content">
	
	<div class="content_area about_area clearfix">
		<div class="section01">
			<h2 class="title01">ABOUT</h2>
			<!-- who_area -->
			<div class="who_area">
				<div class="img">
					<img src="${IMG_PATH }/content/about_img01.png" alt="캐릭터이미지" />
				</div>
				<h3 class="title02">Who I Am</h3>
				<p>Web Developer 이민지 라고 합니다.</p>
				<p>꾸준히 성장하고, 노력하며 더 나은 웹환경을 사용자들에게 보여주기 위해 더욱 더 열심히 하겠습니다.</p>
				<p></p>
				<ul class="clear who_myinfo">
					<li><span class="icon_phone">전화번호</span>${configVo.cf_info1 }</li>
					<li><span class="icon_mail">메일</span> ${configVo.cf_info1 }</li>
					<li><span class="icon_career">직업 </span> Back-end developer & front-end developer</li>
				</ul>
			</div>
			<!-- //who_area -->
			<!-- education_area -->
			<div class="education_area">
				<h3 class="title02">Education</h3>
				<dl class="clear list01">
					<dt><span class="icon_date"></span> 2016-2017</dt>
					<dd>
						<div class="about_line">
						<div class="icon_education">
							자바 사물인터넷 6개월과정수료
							<span class="add_info">2016.11-2016.05</span>
							<span class="add_info">솔데스크</span>
						</div>
						</div>
					</dd>
				</dl>
				<dl class="clear list01">
					<dt><span class="icon_date"></span> 2014-2015</dt>
					<dd>
						<div class="about_line">
						<div class="icon_education">
							방송통신대학교 졸업 / 컴퓨터과학과
							<span class="add_info">2014.03-2016.02</span>
						</div>
						</div>
					</dd>
					<dd>
						<div class="about_line">
						<div class="icon_education">
							정보처리기사 자격증취득
							<span class="add_info">2015.10</span>
						</div>
						</div>
					</dd>
				</dl>

				<dl class="clear list01">
					<dt><span class="icon_date"></span> 2008-2013</dt>
					<dd>
						<div class="about_line">
						<div class="icon_education">
							국민대학교 졸업 / 의상디자인
							<span class="add_info">2008.03-2013.02</span>
						</div>
						</div>
					</dd>
					<dd>
						<div class="about_line">
						<div class="icon_education">
							JLPT 2급 취득
							<span class="add_info">2013.01</span>
						</div>
						</div>
					</dd>
					<dd>
						<div class="about_line">
						<div class="icon_education">
							웹디자인기능사 자격증취득
							<span class="add_info">2013.04</span>
						</div>
						</div>
					</dd>
				</dl>
				<dl class="clear list01">
					<dt><span class="icon_date"></span> 2005.03-2008.02</dt>
					<dd>
						<div class="about_line">
						<div class="icon_education">
							현대고등학교 졸업
							<span class="add_info">울산</span>
						</div>
						</div>
					</dd>
				</dl>
			</div>
			<!-- //education_area -->
		</div>
		
		<div class="section02">
			<!-- work_area -->
			<div class="work_area">
				<h3 class="title02">Work Experience</h3>
				<dl class="clear list01">
					<dt><span class="icon_date"></span> 2015.07 ~ 2016.08</dt>
					<dd>
						<div class="about_line">
						<div class="icon_education">
							㈜재능E아카데미 웹 퍼블리셔 근무
							<span class="add_info">UX팀 사원</span>
						</div>
						</div>
					</dd>
				</dl>
				<dl class="clear list01">
					<dt><span class="icon_date"></span> 2013.10 ~ 2015.07</dt>
					<dd>
						<div class="about_line">
						<div class="icon_education">
							㈜윅슨어소시에이츠 웹 퍼블리셔 근무
							<span class="add_info">퍼블리싱팀 사원</span>
						</div>
						</div>
					</dd>
				</dl>
				<div class="btn_area_center">
					<a href="${PATH }/pf/board/list.do?bo_table=portfolio" class="btn01">대표 프로젝트 보기</a>
				</div>
			</div>
			<!-- //work_area -->
			<!-- myskill_area -->
			<div class="myskill_area">
				<h3 class="title02">My Skills</h3>
				<div class="skill_list">
					<h4>일반</h4>
					<ul class="clear">
						<li>
							<span class="tit">Back end</span>
							<span class="percent">80%</span>
							<span class="gage" style="width:80%"></span>
						</li>
						<li>
							<span class="tit">Front end</span>
							<span class="percent">95%</span>
							<span class="gage" style="width:95%"></span>
						</li>
						<li>
							<span class="tit">Design</span>
							<span class="percent">90%</span>
							<span class="gage" style="width:90%"></span>
						</li>						
					</ul>
				</div>
				<div class="skill_list">
					<h4>프로그래밍 언어</h4>
					<ul class="clear">
						<li>
							<span class="tit">Java (JSP & Servelet)</span>
							<span class="percent">70%</span>
							<span class="gage" style="width:70%"></span>
						</li>
						<li>
							<span class="tit">Android</span>
							<span class="percent">60%</span>
							<span class="gage" style="width:60%"></span>
						</li>												
						<li>
							<span class="tit">PHP</span>
							<span class="percent">70%</span>
							<span class="gage" style="width:70%"></span>
						</li>
						<li>
							<span class="tit">SQL - MySQL</span>
							<span class="percent">80%</span>
							<span class="gage" style="width:80%"></span>
						</li>
						<li>
							<span class="tit">SQL - Oracle</span>
							<span class="percent">70%</span>
							<span class="gage" style="width:70%"></span>
						</li>
						<li>
							<span class="tit">HTML & CSS</span>
							<span class="percent">100%</span>
							<span class="gage" style="width:100%"></span>
						</li>
						<li>
							<span class="tit">HTML5 & CSS3</span>
							<span class="percent">70%</span>
							<span class="gage" style="width:70%"></span>
						</li>
					
					</ul>
				</div>
				<div class="skill_list">
					<h4>개발도구</h4>
					<ul class="clear">
						<li>
							<span class="tit">Eclipse</span>
							<span class="percent">80%</span>
							<span class="gage" style="width:70%"></span>
						</li>
						<li>
							<span class="tit">Android Studio</span>
							<span class="percent">60%</span>
							<span class="gage" style="width:60%"></span>
						</li>												
						<li>
							<span class="tit">EditPlus</span>
							<span class="percent">100%</span>
							<span class="gage" style="width:100%"></span>
						</li>
						<li>
							<span class="tit">Photoshop</span>
							<span class="percent">100%</span>
							<span class="gage" style="width:100%"></span>
						</li>											
					</ul>
				</div>				
			</div>
			<!-- //myskill_area -->
		</div>
	</div>
</section>
<!-- //content -->
<c:import url="../layout/footer.jsp"></c:import>