<?php 
use DBAccess\DBAccess;
require_once 'DBAccess.php';



interface iGenDAO {
	public function report();
	public function create($table, $object);
	public function update($table, $object);
	public function delete($table, $object);
}
class GenDAO implements iGenDAO {
	private $connection;
	
	public function __construct(){
		$this->connection = DBAccess::connect();
	}
	
	public function report() {
		$c = DBAccess::connect();
		return $c->query('SELECT * FROM users');
		
	}
	public function create($table, $object){
		;
	}
	public function update($table, $object){
		
	}
	public function delete($table, $object){
		
	}
}

$a = new GenDAO();
$r = $a->report();
$row = $r->fetch_array(MYSQLI_ASSOC);
printf($row["username"]);


?>