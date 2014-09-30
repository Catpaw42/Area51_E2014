<?php
namespace testSpaceStuff;

class UserDTO {
	
	private $username;
	private $userId;
	private $cpr;
	private $password;
	private $position; // kan muligvis lave vores egen enum klasse - Rùni
	
	function __construct($username="", $cpr=0, $position=array()){
		$this->username = $username;
		$this->cpr = $cpr;
		$this->position = $position;
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