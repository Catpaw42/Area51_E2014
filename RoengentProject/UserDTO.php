<?php
namespace testSpaceStuff;

class UserDTO {
	
	private $username; 
	private $userId;
	var $cpr;		//"var" er en PHP 4 ting, i PHP5 bør du bruge private/protected/public ligesom i java - M
	var $password;
	var $position; // kan muligvis lave vores egen enum klasse - Rùni
	
	function __construct($username=""){
		$this->username = $username;
		return self::class;
	}
	
	function getUserId(){
		return $this->userId;
	}
	function setUserId($userId){
		$this->userId = $userId;
	}
	
	
	function getUsername(){
		return $this->username;
	}
	function setUsername($username){
		$this->username = $username;
	}
	
	function getCpr(){
		return $this->cpr;
	}
	function setCpr($cpr){
		$this->cpr = $cpr;	
	}
	
	function getPosition(){
		return $this->position;
	}
	function setPosition($position){
		$this->position = $position;
	}
	
	
}