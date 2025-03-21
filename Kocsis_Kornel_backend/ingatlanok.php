<?php
$servername = "localhost";
$username = "root";
$password = ""; 
$dbname = "ingatlan";


$conn = new mysqli($servername, $username, $password, $dbname);


if ($conn->connect_error) {
    die("Kapcsolódási hiba: " . $conn->connect_error);
}

class Database {
    private $conn;

    public function __construct($conn) {
        $this->conn = $conn;
    }


    public function getIngatlan() {
        $sql = "SELECT * FROM ingatlanok";  
        $result = $this->conn->query($sql);
        $ingatlanok = [];

        if ($result->num_rows > 0) {
            while ($row = $result->fetch_assoc()) {
                $ingatlanok[] = $row;
            }
        }

        return $ingatlanok;
    }


    public function postIngatlan($nev) {
        $stmt = $this->conn->prepare("INSERT INTO ingatlanok (nev) VALUES (?)");
        $stmt->bind_param("s", $nev); 
        $stmt->execute();
    }

 
    public function deleteIngatlan($id) {
        $stmt = $this->conn->prepare("DELETE FROM ingatlanok WHERE id = ?");
        $stmt->bind_param("i", $id);
        $stmt->execute();
    }
}


$database = new Database($conn);


$ingatlanok = $database->getIngatlan();


if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['nev'])) {
    $nev = $_POST['nev'];
    $database->postIngatlan($nev);
}


if ($_SERVER['REQUEST_METHOD'] == 'DELETE' && isset($_GET['id'])) {
    $id = $_GET['id'];
    $database->deleteIngatlan($id);
}


$conn->close();
?>