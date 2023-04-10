/**
 * reply.js
 */
console.log("start");
//function이라는 정의 구문을 가지고 있는 변수 replyService
//즉각 실행 함수 : 함수에 선언된 기능에 의해 replyService의 객체의 매개값으로 값을 넘겨줌.???
var replyService = (function () {
  var sum = function(a,b){
    return a+b;
  };
  // 댓글 등록
  function add(reply={}, callback){ //reply 댓글 정보를 담을 수 있는 객체 타입을 받아옴. 변수 이름 콜백, 함수 구문을 받아옴.
    $.ajax({
        type: 'post',
        url: '/replies/new',
        //추가적인 속성을 넘기려면 data를 통해서 넘길 수 있음
        data: JSON.stringify(reply), //자바스크립트의 객체타입을{"rno":10,"bno":300,"reply":"댓글내용",...} json포맷으로 바꾸어 uri에 호출하겠다.
        //전송되는 데이터 타입은 content option
        contentType: 'application/json',
        success: function(result){ //result 결과값을 받아오는 함수
           if(callback){
               //console.log(result);
               callback(result); //callback함수의 매개값으로 result(의 값)을 사용.
            }
        },
        error : function(reject){
            console.error(reject);
        }
    })
  } // end of 등록

  // 글번호 -> 댓글정보 보여줌
  function getList(param={}, callback, error){
    // param = {bno: 300}
    var bno = param.bno;
    var page = param.page;

    //get방식임. post아님.
    $.getJSON('/replies/pages/' + bno + '/' + page + '.json', function (data){
        if (callback){
            //console.log(data)
            callback(data);
        }
    }).fail(function(err){
        if(error){
            // console.error(err);
            error(err);
        }
    });
  }

  // 삭제
  function remove(rno, callback, error){
    $.ajax({
        type: 'delete',//method: 동일
        url: '/replies/' + rno,
        success: function(result){
            if(callback){
                callback(result);
            }
        },
        error: function(reject){
            if(error){
                error(reject);
            }
        }
    })
  } // end of remove

  // 수정
  function update(reply={}, callback, error){
    $.ajax({
        type: 'put',
        url:'/replies/'+reply.rno,
        data: JSON.stringify(reply),
        contentType: 'application/json;charset=UTF-8',
        success: function(result){
            if(callback){
                callback(result);
            }
        },
        error: function(reject){
            if(error){
                error(reject);
            }
        }
    })
  } // end of update

  // 단건 조회
  function get(rno, callback, error){
    $.get('/replies/'+rno+'.json', function (result){
        if (callback){
            callback(result);
        }
    }).fail(function (reject){
        if(error)
            error(reject);
    })
  }

  // 날짜 표시
  function displayTime(timeValue){
    // 현재 시간을 기준으로 24시간 이후 => 날짜만
    // 24시간 이내 => 시간을 보여줌
    var today = new Date();
    var gap = today.getTime() - timeValue; // 1680938301000
    var dateObj = new Date(timeValue); //getFullYear, getMonth 등 활용 가능
    //1초(1000ms(밀리세컨드))
    if(gap < (1000 * 60 * 60 * 24)) {
        var hh = dateObj.getHours();
        var mm = dateObj.getMinutes();
        var ss = dateObj.getSeconds();
        // 14:23:17
        return [(hh>9?'':'0')+hh, ':', (mm>9?'':'0')+mm, ':', (ss>9?'':'0')+ss].join(''); // 배열값 join('') 메소드 사용 -> 문자열
    } else {
        var yy = dateObj.getFullYear();
        var mm = dateObj.getMonth()+1;//getMonth는 0부터 시작
        var dd = dateObj.getDate();//getDay는 요일 정보 getDate는 날짜 정보
        return[yy, '/', (mm>9?'':'0')+mm, '/', (dd>9?'':'0')+dd].join('');
    }
  }



  return {
    sum: sum,
    add: add,//add의 함수 정의 구문이 이곳(: add)에 있다는 의미로 받아들여도 됨.
    getList: getList,
    remove: remove,
    update: update,
    get: get,
    displayTime: displayTime
}
})();

// var reply = {bno: 300, reply: 'ajax를 통한 댓글', replyer:'user00'}

// replyService.add(reply, function (result){
//     alert("Result:" + result)
// })
//console.log(replyService.add(reply));