let lecture;

// 검색 화면
function drawTable(list) {
	console.log(list[0]);
	document.getElementById("searchList").innerHTML = ``;
	let title = document.querySelector('#searchLecture').value;
	let h2 = document.createElement("h2");
	h2.innerText = '\'' + title + '\' 검색 결과';
	h2.setAttribute('style', 'font-size: 25px;');
	document.getElementById("searchList").appendChild(h2);

	let div = document.createElement("div");
	div.classList.add('container');

	let div2 = document.createElement("div");
	div2.classList.add('row');
	div2.classList.add('g-4');
	div2.classList.add('justify-content-center');
	div.appendChild(div2);
	//강의 하나 섹션 반복문
	list.forEach(function(item) {
		//<div class="col-lg-4 col-md-6 wow fadeInUp">
		console.log(item['category']);
		let div3 = document.createElement("div");
		div3.classList.add('col-lg-4');
		div3.classList.add('col-md-6');
		div3.classList.add('wow');
		div3.classList.add('fadeInUp');

		//<div class="course-item bg-light">
		let div4 = document.createElement("div");
		div4.classList.add('course-item');
		div4.classList.add('bg-light');

		//이미지랑 카테고리 담는 div5. div4.appendChild(div5)
		//<div class="position-relative overflow-hidden">
		let div5 = document.createElement("div");
		div5.classList.add('position-relative');
		div5.classList.add('overflow-hidden');

		//<img class="img-fluid" src="../../img/course-1.jpg" alt="" onclick="location.href='course-detail.html';">
		let img = document.createElement('img'); // <img></img>
		img.classList.add('img-fluid');
		img.src = '../../momolearn/img/lecture/' + item['image'];
		// location.href="/StdGroup/insert/" + item['roomNo']
		// id로 검색해서 강의 상세보기
		img.onclick = function() {
			location.href = '../../momolearn/lecture/detailLecture/' + item['id'];
		}
		div5.appendChild(img);

		//<div class="w-100 d-flex justify-content-center position-absolute bottom-0 start-0 mb-4">
		let div6 = document.createElement("div");
		div6.classList.add('w-100');
		div6.classList.add('d-flex');
		div6.classList.add('justify-content-center');
		div6.classList.add('position-absolute');
		div6.classList.add('bottom-0');
		div6.classList.add('start-0');
		div6.classList.add('mb-4');

		//JSON으로 파싱한 다음에 하나씩 꺼내오기
		JSON.parse(item['category']).forEach(function(cate) {
			console.log(cate);
			//<a href="#" class="flex-shrink-0 btn btn-sm btn-primary px-3 border-end" style="border-radius: 30px;">카테고리명</a>
			let a = document.createElement('a'); // <a></a>
			a.classList.add('flex-shrink-0');
			a.classList.add('btn');
			a.classList.add('btn-sm');
			a.classList.add('btn-primary');
			a.classList.add('px-3');
			a.classList.add('border-end');
			a.style.borderRadius = '30px';
			a.innerText = cate;
			div6.appendChild(a);
		});

		div5.appendChild(div6);
		div4.appendChild(div5);
		div3.appendChild(div4);

		//<div class="text-center p-4 pb-0"> div4에 들어감
		let div7 = document.createElement("div");
		div7.classList.add('text-center');
		div7.classList.add('p-4');
		div7.classList.add('pb-0');

		//div7에 들어감
		let h3 = document.createElement("h3");
		h3.classList.add('mb-0');
		h3.innerText = item['title'];
		let p = document.createElement("p");
		p.classList.add('mb-4');
		p.innerText = item['info'];
		let h5 = document.createElement("h5");
		h5.classList.add('mb-4');
		h5.innerText = item['price'];

		div7.appendChild(h3);
		div7.appendChild(p);
		div7.appendChild(h5);

		div4.appendChild(div7);

		//div4에 들어감
		let div8 = document.createElement("div");
		div8.classList.add('text-center');
		div8.classList.add('p-4');

		let small1 = document.createElement('small');
		small1.classList.add('flex-fill');
		small1.classList.add('text-center');
		small1.classList.add('border-end');
		small1.classList.add('py-2');
		
		let i1 = document.createElement('i');
		i1.classList.add('fa');
		i1.classList.add('fa-user-tie');
		i1.classList.add('text-primary');
		i1.classList.add('me-2');
		small1.appendChild(i1);
		small1.innerText = item['teacher'];

		let small2 = document.createElement('small');
		small2.classList.add('flex-fill');
		small2.classList.add('text-center');
		small2.classList.add('border-end');
		small2.classList.add('py-2');
		let i2 = document.createElement('i');
		i2.classList.add('fa');
		i2.classList.add('fa-clock');
		i2.classList.add('text-primary');
		i2.classList.add('me-2');
		small2.appendChild(i2);
		small2.innerText = '총 '+ item['cnt'] +'강';

		let small3 = document.createElement('small');
		small3.classList.add('flex-fill');
		small3.classList.add('text-center');
		small3.classList.add('py-2');
		let i3 = document.createElement('i');
		i3.classList.add('fa');
		i3.classList.add('fa-user');
		i3.classList.add('text-primary');
		i3.classList.add('me-2');
		small3.appendChild(i3);
		small3.innerText = item['applyCnt'] + '명 수강중';

		div8.appendChild(small1);
		div8.appendChild(small2);
		div8.appendChild(small3);
		
		div4.appendChild(div8);
		div3.appendChild(div4);
		div2.appendChild(div3);

	});

	//table.appendChild(tr);
	return document.getElementById("searchList").appendChild(div);
}


// 강의 검색
function dataReceive(title) {
	axios({
		method: "GET",
		url: "../../momolearn/lectures/searchLecture/" + title
	}).then(function(resData) {
		lecture = resData.data;
		console.log('넘어온 데이터' + lecture);
		// data타입이 object가 아니면 json이 아닌 예외 메세지가 왔다는 뜻
		if (typeof (lecture) == "string") {
			alert("실행중 문제 발생 : " + lecture);
		} else if (typeof (lecture) == "object") {
			drawTable(lecture);
		}
	}).catch(function() {
		alert("실행중 문제 발생 : " + "값을 입력해주세요.");
	});
}
//id=btn인 버튼을 클릭시 발생
document.querySelector('#btn').addEventListener('click', function() {
	// title: searchLecture input 태그의 값
	let title = document.querySelector('#searchLecture').value;
	console.log(title);
	dataReceive(title);
	title.innerText = '';
});