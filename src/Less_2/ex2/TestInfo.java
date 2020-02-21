package Less_2.ex2;

import java.sql.*;

public class TestInfo {
    /**
     * Рабочий SQL Server
     * База данных для примера
     * Драйвера для подключения
     * Собственно, Java
     *
     *
     *
     *2. Класс Connection
     * Класс java.sql.Connection представляет в JDBC сеанс работы с базой данных.
     * Он предоставляет приложению объекты Statement (и его подклассы) для этого сеанса.
     * Он также управляет транзакциями для этих команд. По умолчанию каждая команда выполняется в отдельной транзакции.
     * Объект Connection позволяет отключить функцию Autocommit автоматического завершения транзакции.
     * В этом случае требуется явно завершить транзакцию, иначе результаты выполнения всех команд будут потеряны.
     *
     * Основные методы:
     * public void close() throws SQLException
     * Этот метод позволяет вручную освободить все ресурсы, такие как сетевые соединения и блокировки базы данных, связанные с данным объектом Connection. Этот метод автоматически вызывается при сборке мусора; лучше, однако, вручную закрыть Connection, если вы в нем больше не нуждаетесь.
     *
     * public Statement createStatement() throws SQLException
     * public Statement createStatement(int type, int concur) throws SQLException
     * Метод создает объект Statement, связанный с сеансом Connection.
     * Версия без аргументов создает объект Statement,
     * для которого экземпляры ResultSet имеют тип только для чтения и перемещения в прямом направлении.
     *
     * public boolean getAutoCommit() throws SQLException
     * public void setAutoCommit(boolean ac) throws SQLException
     * По умолчанию все объекты Connection находятся в режиме автозавершения. В этом режиме каждая команда завершается сразу после выполнения. Может оказаться предпочтительнее вручную завершить серию команд в приложении как единую транзакцию. В этом случае метод setAutoCommit() используется для отключения автозавершения. Затем, после выполнения своих команд, вы вызываете commit() или rollback(), в зависимости от успеха или неуспеха транзакции. В режиме автозавершения команда завершается, когда она выполнена, или выполняется следующая команда, в зависимости от того, что произойдет раньше. Команда, возвращающая ResultSet, выполнена, когда извлечена последняя строка или закрыт объект ResultSet. Если команда возвращает множественные результирующие наборы, завершение происходит после извлечения последней строки последнего объекта ResultSet.
     *
     * public void commit() throws SQLException
     * Этот метод делает постоянными изменения, произведенные всеми командами, связанными с данным соединением и выполненными вслед за последней командой завершения или отката транзакции. Использовать его следует только при отключенном автозавершении. Он не завершает изменения, сделанные командами, которые связаны с другими объектами Connection.
     *
     * public String getCatalog() throws SQLException
     * public void setCatalog(String catalog) throws SQLException
     * Если драйвер поддерживает каталоги,
     * то setCatalog() используется для выбора подпространства базы данных с заданным именем каталога.
     * Если драйвер каталоги не поддерживает, запрос игнорируется.
     *
     * public DatabaseMetaData getMetaData() throws SQLException
     * Класс DatabaseMetaData предоставляет методы, описывающие таблицы базы данных, поддержку SQL,
     * хранимые процедуры и другие сведения, относящиеся к базе данных и данному Connection,
     * которые не относятся непосредственно к выполнению команд и извлечению результирующих наборов.
     * Метод создает экземпляр класса DatabaseMetaData для данного Connection.
     *
     * public SQLWarning getWarnings() throws SQLException
     * Возвращает первое предупреждение из списка, связанного с данным объектом Connection.
     *
     * 3. Класс Statement
     * Класс представляет встроенную команду SQL и используется приложением для доступа к базе данных.
     * При закрытии Statement автоматически закрываются все связанные с ним открытые объекты ResultSet.
     *
     * Основные методы:
     * public void addBatch(String sql) throws SQLException
     * Добавляет заданную команду SQL к текущему пакету команд.
     *
     * public void cancel() throws SQLException
     * В многопоточной среде с помощью этого метода можно потребовать прекращения всякой обработки,
     * связанной с данным Statement. В этом смысле метод аналогичен методу stop() для объектов Thread.
     *
     * public boolean execute(String sql) throws SQLException
     * public ResultSet executeQuery(String sql) throws SQLException
     * public int executeUpdate(String sql) throws SQLException
     * Выполняет Statement, передавая базе данных заданную SQL-строку.
     * Первый метод, execute(), позволяет вам выполнить Statement, когда неизвестно заранее,
     * является SQL-строка запросом или обновлением.
     * Метод возвращает true, если команда создала результирующий набор.
     *
     * Метод executeQuery() используется для выполнения запросов (на извлечение данных).
     * Он возвращает для обработки результирующий набор.
     *
     * Метод executeUpdate() используется для выполнения обновлений.
     * Он возвращает количество обновленных строк.
     *
     * public int[ ] executeBatch(String sql) throws SQLException
     * Посылает базе данных пакет SQL-команд для выполнения.
     * Возвращает массив чисел, описывающих количество строк, затронутых каждой командой SQL.
     *
     * public ResultSet getResultSet() throws SQLException
     * Метод возвращает текущий ResultSet.
     * Для каждого результата его следует вызывать только однажды. Его не нужно вызывать после обращения к executeQuery(), возвращающему единственный результат.
     *
     * public void close() throws SQLException
     * Вручную закрывает объект Statement. Обычно этого не требуется, так как Statement автоматически закрывается при закрытии связанного с ним объекта Connection. К сожалению, не все разработчики JDBC-драйверов придерживаются этих конвенций, так что настоятельно советую закрывать вручную, как это показано в примере.
     *
     *
     *
     *
     *
     * 4. Класс ResultSet
     * Этот класс представляет результирующий набор базы данных.
     * Он обеспечивает приложению построчный доступ к результатам запросов в базе данных.
     * Во время обработки запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
     * Приложение последовательно перемещается по результатам,
     * пока они не будут все обработаны или не будет закрыт ResultSet.
     *
     * Основные методы:
     * public boolean absolute(int row) throws SQLException
     * Метод перемещает курсор на заданное число строк от начала, если число положительно,
     * и от конца - если отрицательно.
     *
     * public void afterLast() throws SQLException
     * Этот метод перемещает курсор в конец результирующего набора за последнюю строку.
     *
     * public void beforeFirst() throws SQLException
     * Этот метод перемещает курсор в начало результирующего набора перед первой строкой.
     *
     * public void deleteRow() throws SQLException
     * Удаляет текущую строку из результирующего набора и базы данных.
     *
     * public ResultSetMetaData getMetaData() throws SQLException
     * Предоставляет объект метаданных для данного ResultSet. Класс ResultSetMetaData содержит информацию о результирующие таблице, такую как количество столбцов, их заголовок и т.д.
     *
     * public int getRow() throws SQLException
     * Возвращает номер текущей строки.
     *
     * public Statement getStatement() throws SQLException
     * Возвращает экземпляр Statement, который произвел данный результирующий набор.
     *
     * public boolean next() throws SQLException
     * public boolean previous() throws SQLException
     * Эти методы позволяют переместиться в результирующем наборе на одну строку вперед или назад.
     * Во вновь созданном результирующем наборе курсор устанавливается перед первой строкой,
     * поэтому первое обращение к методу next() влечет позиционирование на первую строку.
     * Эти методы возвращают true, если остается строка для дальнейшего перемещения.
     * Если строк для обработки больше нет, возвращается false.
     * Если открыт поток InputStream для предыдущей строки, он закрывается.
     * Также очищается цепочка предупреждений SQLWarning.
     *
     * public void close() throws SQLException
     * Осуществляет немедленное закрытие ResultSet вручную.
     * Обычно этого не требуется, так как закрытие Statement, связанного с ResultSet, автоматически закрывает ResultSet.
     * К сожалению, не все разработчики JDBC-драйверов придерживаются этих конвенций, например,
     * драйвер Oracle самостоятельно не закрывает ResultSet'ы.
     */

    /**
     * https://www.pgadmin.org/download/pgadmin-4-windows/
     * https://www.postgresql.org/download/windows/
     */

    private static Connection con = null;
    private static String username = "postgres";
    private static String password = "root";
    private static String URL = "jdbc:postgresql://localhost/test";

    public static void main(String[] args) throws SQLException {
//        DriverManager.registerDriver(new net.sourceforge.jtds.jdbc.Driver());
        //Загружаем драйвер
        con = DriverManager.getConnection(URL, username, password);
        //соединяемся
        if(con!=null) System.out.println("Connection Successful !\n");
        if (con==null) System.exit(0);
        Statement st = con.createStatement();
        //Statement позволяет отправлять запросы базе данных
        ResultSet rs = st.executeQuery(" SELECT * FROM public.users2");
        //ResultSet получает результирующую таблицу
        int x = rs.getMetaData().getColumnCount();
        //Resultset.getMetaData() получаем информацию
        //результирующей таблице
        while(rs.next()){
            for(int i=1; i<=x;i++){
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }
        System.out.println();
        if(rs!=null)rs.close();
        if(st!=null)st.close();
        if(con!=null)con.close();
    }
}
