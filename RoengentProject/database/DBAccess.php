<?php
namespace DBAccess;
/** 
 *  
 * @author Christian
 * Database Connector class - Singleton pattern
 */
class DBAccess
{
	private static $connection = null;
	

	public function __construct()
	{
		die('No calls to constructor allowed - use connect() to get connection');

	}
	/**
	 *
	 * @param string $host Either a host name or an IP address
	 * @param string $username The MySQL user name
	 * @param string $password The password to log in with
	 * @param string $dbname The default database to be used when performing queries
	 */
	
	public static function connectparam($host, $user, $password, $database){
		//Singleton pattern
		if (self::$connection == null){
			self::$connection = new \mysqli($host, $user, $password, $database);				
		}
		return self::$connection;
		
	}
	public static function connect(){
		return self::connectparam('sql-lab1.cc.dtu.dk', 's134000', 'hastings', 's134000');
	}
	/**
	 * Disconnecting from DB
	 */
	public static function disconnect(){
		self::$connection=null;
	}
	
	public function printDatabases()
	{
		$statement = $this->connection->prepare("SHOW DATABASES");
		$statement->execute();
		$results = $statement->get_result();
		var_dump($results->fetch_all());
	}
}