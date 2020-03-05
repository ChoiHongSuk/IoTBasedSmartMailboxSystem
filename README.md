# IoT based Smart Mailbox System
서버와 세 개의 디바이스를 통해 사용자가 언제, 어디서나 우편함의 우편물의 유무를 알 수 있는 IoT시스템을 구현
스마트 메일함은 NodeMCU를 중심으로 우편물을 카운트 해주는 적외선센서와 적외선 센서를 조작하는 자석센서로 구성되고, 홀로그램 시계는 라즈베리파이와 5인치 디스플레이로 구성된다.

# Usage Tech
HTML, JSP, JavaFX, C, Apache TomCat, NodeMCU, RaspberryPi 3+, Android Studio

# System Architecture
> - Service Model
> > <img src="https://user-images.githubusercontent.com/43469662/76012725-20c99a80-5f5a-11ea-8cac-09eff12e9eb2.png" height="400"></br>
> > &nbsp;배송된 우편물을 스마트 메일함을 통해 물리적 데이터를 카운트하고 웹서버를 통해 DB(DataBase)에 저장한 후 홀로그램 시계나 안드로이드 어플리케이션을 통해 사용자에게 실시간 데이터를 보여주는 서비스 모델</br>
 &nbsp;무선 네트워크를 통해  웹서버 통신을 시도하기에 상대적으로 먼 거리에서도 원활하게 통신이 가능하며 향후 다른 스마트 디바이스들을 추가적으로 연결할 수 있는 확장의 다양성을 가지고 있다. 그러나 디바이스들 간의 웹서버를 활용한 데이터 교환이 중점이기에 원활한 웹서버 운영과 DB의 효율적 데이터 관리가 매우 중요하다.
> - System Model
> > <img src="https://user-images.githubusercontent.com/43469662/76012581-dea05900-5f59-11ea-9b51-4f5304b0ad8f.png" height="400">
> > IoT시스템의 플로우 차트는 그림 3와 같이 데이터를 전송하는 스마트 메일함을 중심으로 표현했으며, 스마트 메일함에 우편물 데이터가 들어올 때마다 무선네트워크에 접속해 웹서버와 신뢰 확인 후 전송하는 흐름도를 나타낸다.

# Flow Chart
> - System Flow Chart
> > <img src="https://user-images.githubusercontent.com/43469662/76013153-d7c61600-5f5a-11ea-9ebf-885f7ef93b56.png">
> - Smart Mailbox Flow Chart
> > <img src="https://user-images.githubusercontent.com/43469662/76013162-dc8aca00-5f5a-11ea-8e17-64408b1e173e.png">
> - Hologram Watch Flow Chart
> > <img src="https://user-images.githubusercontent.com/43469662/76013170-e01e5100-5f5a-11ea-9abf-a0524d7a3af4.png">
> - Android Application Flow Chart
> > <img src="https://user-images.githubusercontent.com/43469662/76013177-e3194180-5f5a-11ea-9ead-79481bc250a0.png">
> - Web Server Flow Chart
> > <img src="https://user-images.githubusercontent.com/43469662/76013193-e57b9b80-5f5a-11ea-9203-d972179e37f2.png">
