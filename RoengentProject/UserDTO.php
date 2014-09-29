<?php
namespace testSpaceStuff;

class UserDTO {
	
	var $username;
	var $userId;
	var $cpr;
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