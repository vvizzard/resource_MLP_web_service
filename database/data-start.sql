INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('mobile', '$2a$10$QXX4cTiIr5UgPNmPr9n3VugECuoPeLDYw8v7.Ml0ZdWLyNcqyQnbq', 'apps,read,write',
	'password,authorization_code,refresh_token,client_credentials', null, null, 36000, 36000, null, true);
	