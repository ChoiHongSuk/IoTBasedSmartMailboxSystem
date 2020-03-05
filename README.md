# IoT based Smart Mailbox System
서버와 세 개의 디바이스를 통해 사용자가 언제, 어디서나 우편함의 우편물의 유무를 알 수 있는 IoT시스템을 구현
스마트 메일함은 NodeMCU를 중심으로 우편물을 카운트 해주는 적외선센서와 적외선 센서를 조작하는 자석센서로 구성되고, 홀로그램 시계는 라즈베리파이와 5인치 디스플레이로 구성된다.

# Usage Tech
HTML, JSP, JavaFX, C, Apache TomCat, NodeMCU, RaspberryPi 3+, Android Studio

# System Architecture
> - Service Model
> > <img src="https://user-images.githubusercontent.com/43469662/76012725-20c99a80-5f5a-11ea-8cac-09eff12e9eb2.png" height="400"></br></br>
> > &nbsp;&nbsp;배송된 우편물을 스마트 메일함을 통해 물리적 데이터를 카운트하고 웹서버를 통해 DB(DataBase)에 저장한 후 홀로그램 시계나 안드로이드 어플리케이션을 통해 사용자에게 실시간 데이터를 보여주는 서비스 모델</br>
 &nbsp;&nbsp;무선 네트워크를 통해  웹서버 통신을 시도하기에 상대적으로 먼 거리에서도 원활하게 통신이 가능하며 향후 다른 스마트 디바이스들을 추가적으로 연결할 수 있는 확장의 다양성을 가지고 있다. 그러나 디바이스들 간의 웹서버를 활용한 데이터 교환이 중점이기에 원활한 웹서버 운영과 DB의 효율적 데이터 관리가 매우 중요하다.
> - System Model
> > <img src="https://user-images.githubusercontent.com/43469662/76012581-dea05900-5f59-11ea-9b51-4f5304b0ad8f.png" height="400"></br></br>
> > &nbsp;&nbsp;데이터를 전송하는 스마트 메일함을 중심으로 표현했으며, 스마트 메일함에 우편물 데이터가 들어올 때마다 무선네트워크에 접속해 웹서버와 신뢰 확인 후 전송하는 흐름도를 나타낸다.

# Flow Chart
> - Smart Mailbox Flow Chart
> > <img src="https://user-images.githubusercontent.com/43469662/76013162-dc8aca00-5f5a-11ea-8e17-64408b1e173e.png">
> > &nbsp;&nbsp;스마트 메일함 플로우 차트에서 스마트 메일함의 문이 열고 닫힘에 따라 적외선 센서를 가동하거나 데이터를 ‘0’으로 초기화할지 결정되며, 문이 닫힌 상태에서 우편물이 들어오면 적외선 센서가 들어온 우편물을 카운트하며 동시에 무선네트워크 접속 상태를 진단하고 문제없으면 데이터를 웹서버로 Post 전송한다. 이후 웹서버 측에서 Response Payload를 보내면 NodeMCU는 이를 확인하고 네트워크  연결을 종료한다.
> - Hologram Watch Flow Chart
> > <img src="https://user-images.githubusercontent.com/43469662/76013170-e01e5100-5f5a-11ea-9abf-a0524d7a3af4.png"></br></br>
> > &nbsp;&nbsp;홀로그램 시계 플로우 차트는 라즈베리파이의 자바FX환경에서 동작하며 시작 시 Main에서 디자인 FXML을 로드하고 각 기능을 Controller 클래스를 통해 조정하는 구조를 갖는다.</br>
&nbsp;&nbsp;Controller는 Platform의 runlater메소드라는 자바FX만의 쓰레드를 사용해 GUI상에서 시간, 요일, 날씨, 우편물 현황 등의 가변적인 데이터를 갱신하며, 날씨와 우편물은 각기 다른 클래스에서 데이터를 별개로 리턴한다. 이때 날씨는 기상청 XML을 document 클래스를 사용해서 그 요소를 추출하여 GUI에 표시하도록 했으며, case문을 통해 이미지로 변경하여 날씨에 따라 배경이미지를 수시로 전환하도록 코딩하였고,  우편물 현황은 Jsoup 외부 API를 활용해 웹서버 HTML을 추출하여 GUI에 표시하도록 하였다.</br>
&nbsp;&nbsp;또한 네트워크 불통 등의 연결문제가 발생했을 때 이로 인해 NULL값이 전체 혹은 부분적으로 발생하면 연결이 정상화될 때까지 연결 재시도를 반복한다. 그 동안은 GUI에 Time, Week, Weather, Mail의 문자가 나타나며 다시 연결될 경우 정상적으로 GUI에 정보가 표시된다.
> - Android Application Flow Chart
> > <img src="https://user-images.githubusercontent.com/43469662/76013177-e3194180-5f5a-11ea-9ead-79481bc250a0.png"></br></br>
> > &nbsp;&nbsp;안드로이드 어플리케이션 플로우 차트의 기본 레이아웃은 ListView를 사용하며, 데이터를 실시간으로 가져오는 과정을 AsyncTask 쓰레드를 사용해 구현하였다.</br>&nbsp;&nbsp;
AsyncTask는 preExecute(), doInBackground(), onPostExecute()의 세 가지 메소드로 구분되며, preExecute()는 doInBackground()가 실행되는 동안 로딩메시지를 출력하고, doInBackground()는 웹서버 url에 접속하고 추출한 데이터 값을 HashMap에 저장 후 ListView Adapter 클래스로 보내 포지션 Id값을 할당하고 데이터를 ListView 형태로 표현한다.</br>&nbsp;&nbsp;
이렇게 표현된 ListView는 onPostExcute()를 통해 실행되며, 웹서버에 데이터가 갱신될 때마다 사용자는 SwipRefreshLayout을 사용해 화면을 아래로 끌어당김으로써 갱신되는 데이터를 실시간으로 열람할 수 있다.
> - Web Server Flow Chart
> > <img src="https://user-images.githubusercontent.com/43469662/76013193-e57b9b80-5f5a-11ea-9203-d972179e37f2.png"></br></br>
> > &nbsp;&nbsp;웹서버 플로우 차트의 흐름은 먼저 데이터가 add페이지를 통해 들어오면 userDAO클래스로 보냄과 동시에 index페이지로 전환한다.</br>&nbsp;&nbsp;
userDAO로 들어온 데이터는 dataBaseUtill의 접속권한 정보를 드라이버를 통해 입력받은 후 MySQL에 접근하는 순서를 밟으며 MySQL Table에 들어온 시간 값과 함께 저장한다.</br>&nbsp;&nbsp;
delete페이지는 add페이지와 반대로 해당 데이터의 시간 값을 받으며, 이후로 동일한 과정을 통해 MySQL Table의 데이터를 삭제한다.</br>&nbsp;&nbsp;
 MySQL Table에 저장/삭제 과정을 거쳤으면, Index페이지는 자체적으로 MySQL에 접근하여 MySQL Table의 데이터를 가져와 웹서버 HTML상에 표시하며, 해당 데이터가 필요한 디바이스들은 웹서버 HTML을 파싱하여 사용한다.
