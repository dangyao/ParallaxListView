## ParallaxListView
===================================      
防QQ图片墙，下拉带视差效果的listview    
---------------------------------------------------------                          
###使用gradle依赖：            
     
在build.gradles添加:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

在dependencies添加:

	dependencies {
	        compile 'com.github.dangyao:ParallaxListView:1.0'
	}

---------------------------------------------------------          
###使用maven依赖：

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
	
	
	<dependency>
	    <groupId>com.github.dangyao</groupId>
	    <artifactId>ParallaxListView</artifactId>
	    <version>1.0</version>
	</dependency>




	      
<div align=center><img width="250" height="350" src="https://github.com/dangyao/ParallaxListView/blob/master/screens/screenshot.gif"/></div>
