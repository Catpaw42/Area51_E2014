<?php
namespace DBAccess;

class DBAccess
{
	private $connection = null;
	
	/**
	 * 
	 * @param string $host Either a host name or an IP address
	 * @param string $username The MySQL user name
	 * @param string $password The password to log in with
	 * @param string $dbname The default database to be used when performing queries
	 */
	public function __construct($host, $username, $password, $dbname)
	{
								// et "\" foran et kald flytter kaldet til globalt-namespace.
								//(vigtig hvis man selv havde lavet en mysqli funktion/classe)
		$this->connection = new \mysqli($host, $username, $password, $dbname);
	}
	
	public function printDatabases()
	{
		$statement = $this->connection->prepare("SHOW DATABASES");
		$statement->execute();
		$results = $statement->get_result();
		var_dump($results->fetch_all());
	}
}