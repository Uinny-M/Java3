package Less_2.Chat.network;

import java.net.ServerSocket;
import java.net.Socket;

public interface ServerSocketThreadListener {
    void onServerStart(ServerSocketThread thread);
    void onServerStop(ServerSocketThread thread);
    void onServerSocketCreated(ServerSocketThread thread, ServerSocket server);
    void onServerTimeout(ServerSocketThread thread, ServerSocket server);
    void onSocketAccepted(ServerSocketThread thread, ServerSocket server, Socket socket);
    void onServerException(ServerSocketThread thread, Throwable exception);
}

/*
1. непонятно как связывается сокет с сокетом
В конструкторе SocketThread мы создаем Socket socket и присваеваем ему(входящий сокет) который создается(c IPадресом и портом) в методе connect() Далее запускается SocketThread получается обращается к входящему сокету и просит у него вход /выход?
DataInputStream in = new DataInputStream(socket.getInputStream());
out = new DataOutputStream(socket.getOutputStream());
Ну получил он вход/выход и непонятно куда он их несет и где подлкючает к другому вход выходу, скажем так простыми словами
2.Не могли бы вы построчно пояснить как работает метод Run у ServerSocketThread - как там происходит выход из цикла и взаимодействие с ожиданием server.setSoTimeout(timeout);
3.Почему в методе RUN (SocketThread)
String msg = in.readUTF(); //слушаем сообщения
listener.onReceiveString(this,socket, msg) отдавая листнеру что к нам пришло а в отдельном методе мы записываем out.writeUTF(msg); как до клиента доходит сообщение?
4.Не могли бы вы например рассказать пару цепочек событий например - нажали на логин - что происходит как работает программа, ну типо сработал метод connect() запустился поток такой то он создал то то; - отправили сообщение в Лог, как это сообщение отправляется, собственно читается(принимается);

1) У нас есть массив в Юзерами, как мы будем реализовывать вывод реально подключенных пользователей? складывать всех авторизованных пользователей так же в какую нибудь коллекцию? что то на подобии того же самого Вектора, как я поняла у нас для этого может быть только JList?
2) В методе onSocketStart мы выводим пользователю сообщение "Start", учитывая что мы выводим пользователям в любой ситуации сообщение о коннекте или дисконнекте обязательно ли этот старт вообще выводить?(или это чисто нам для наглядности?)
3) На сколько сложно реализовать такие темы как опаределение таймзоны и локейшена пользователя для вывода сообщений в консоль на языке страны, в которой он находиться? Или внедрение сохраняемых настроек для выбора языка работы приложения(который показывается пользователю.)

Как проверить, что сообщения на сервере видят все авторизованные пользователи? как в Идеи подключиться одновременно с нескольких клиентов?

Поподробней пояснить чем занимается объект statement и ResultSet
Почему мы в onSocketReady в ClientGUI используем скрытие вверха и отображение низа до результата входа пользователя? т.е после нажатия подключения происходит изменение формы, если пользователь не подключился она снова меняется.
На чем основано решение в интрефейсе SocketThreadListener (для примера) передавать и поток с сокетом и сам сокет? нельзя из потока брать этот сокет когда он необходим в методах?
1)Можно ли в кратце рассказать про Library?

3)Будем ли мы делать добавление реальных юзеров в "userList" или это нужно будет сделать самостоятельно?

Хотел бы спросить вашего совета.
Хочу сделать паузу и еще раз пройтись по Java 1 - 2, что бы повторить.
Стоит ли идти на java 3 и потом делать паузу, или лучше сделать до?

1 Правильно ли я понял, что класс Library определяет текст сообщений, которые мы используем для информирования пользователя о совершенном действии и успешно оно (действие) или нет?
2 Вопрос про подключение к БД, если у БД будет User и Password, где их указывать, в getConnection()?

что занчит потокозащищенный?

Почему в Vector также попадают неавтоаризованные пользоватлеи? Vector - массив для харанения всех сокетов полученных от клиента?

CleintTrhead extends SocketThread? зачем мы наследуемя? Что это нам дает?
Запутался в структуре проекта. Было бы великолепно увидеть блок-схему, UML-диграмму...

На сколько то как мы пишем этот чат в реальности соотносится с написанием рабочей программы, чаще сперва идёт как можно конкретнее прорисовка всех элементов программы или же всё обстоит примерно так, как у нас на уроках (сперва написали так, потом заменили и написали по другому , добавили скуль, добавили лисенеры и т.д) Или же, то как мы пишем программу на уроке сделано чтоб лучше охватить материал и усвоить именно логику написания?

В реальности sqlite используют вообще для хранения паролей? файл бд ведь не защищен паролем в отличие от sql сервера.
Если используют, то достаточно хотя бы пароли хэшировать (а не хранить в первоначальном виде) перед записью в файл бд? И уже тогда при запросе авторизации необходимо переводить принятый от пользователя пароль в хэшкод и сверять также как мы сверяем запросом есть ли такая пара полей (логин, пароль) в бд?

1). По поводу DELIMITER в Library вы говорили: "проще всего использовать пробел, но тогда при broadcast сообщениях, если мы будем внутри broadcast сообщения давать через пробел bradcast msg и само сообщение тоже будет содежать пробелы, то у нас будет всё-всё-всё делиться". Я не пониаю как будет выглядеть эта "поломка" на деле, почему сломается
2) класс SQLClient нужен для связи клиента с бд ?
3) private void handleNonAuthMessage(ClientThread client, String msg) {
String[] arr = msg.split(library.DELIMITER); -
Массив, потому что мы заранее знаем, что сообщение пользователя, это префикс + логин + пароль
4) Курс по SQL я пока не проходил, так что любой доп. информации буду рад
(PS. Полсе закрытия SQLite наша таблица же не пропадет, а где именно она хранится ?)

1. Почему в SqlClient при дисконекте мы не сделали проверку на отсутствие конекта?
2. onServerSocketCreated мы его ни как не используем? (так Идея говорит)
3. Что происходит на данной строке log.setCaretPosition(log.getDocument().getLength());
4. Почему DataOutputStream out мы создаем не в потоке как мы это делаем с DataInputStream.
5. Можно еще раз по листнерам. Имплементим к тому кто хочет его слушать, а создаем там где хотим через него сообщать?

Запутался в коде, Что у нас выводит вот эти строки?
Start
/auth_accept±ivan-igorevich
/bcast±1581334805694±Server±ivan-igorevich connected

в момент создания ChatServerListener мы его не имплементировали в ChatServer как интерфейс, а подали на вход через конструктор. не совсем ясно, когда делать так, а когда имплементировать.
try с ресурсами и без (не было у меня на джаве1), когда оптимальнее применять какой вариант. спасибо

3 Когда мы реализуем запрос к БД, там указано %s и в логине и в пароле, в метод передаем отдельно логин, отдельно пароль, как работает эта магия класса String?

2)Class.forName("org.sqlite.JDBC"); - что делает эта строка?
статическая конструкция static {....}


* */
