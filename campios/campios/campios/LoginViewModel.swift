//
//  LoginViewModel.swift
//  campios
//
//  Created by Camp - 2024 on 23/04/24.
//

import Foundation

class LoginViewModel: ObservableObject {
    
    @Published var username: String = ""
    @Published var password: String = ""
    let loginRequestProtocol: LoginRequestProtocol
    
    init(username: String = "", password: String = "", loginRequestProtocol: LoginRequestProtocol = LoginRequest()) {
        self.username = username
        self.password = password
        self.loginRequestProtocol = loginRequestProtocol
    }
    
    func enterButtonAction() {
        debugPrint(username)
        debugPrint(password)
    }
    
}

public protocol LoginRequestProtocol {
    
    func login(username: String, password: String,
               completion: @escaping (Result<LoginResponse, LoginError>) -> ()
    )
    
}

public struct LoginRequest: LoginRequestProtocol {
    
    public func login(username: String, password: String,
                      completion: @escaping (Result<LoginResponse, LoginError>) -> ()
    ) {
        
        guard let url = URL(string: "https://keyclock.cluster-dev.ioasys.com.br/realms/camp-ioasys-2024/protocol/openid-connect/token") else {
            completion(.failure(.urlNotFound))
            return
        }
        var urlRequest = URLRequest(url: url)
        urlRequest.httpMethod = "POST"
        urlRequest.addValue("application/x-www-form-urlencoded", forHTTPHeaderField: "Content-Type")
        let parameters = [
            "client_id":"camp-ioasys-2024",
            "client_secret": "BiKzHxr9ZoZRDlLjx6qG7QfnDhIoQdIf",
            "username": "username",
            "password": "tph4hyk!BZC2txm*mcb",
            "grant_type": "password"
        ]
        let formParameters = parameters.map { ( key, value ) in
            return "\(key)=\(value)"
        }.joined(separator: "&")
        
        
        urlRequest.httpBody = formParameters.data(using: .utf8)
        
        URLSession.shared.dataTask(with: urlRequest) { (data, response, error) in
            DispatchQueue.main.async {
                guard let httpResponse = response as? HTTPURLResponse else {
                    return
                }
                switch httpResponse.statusCode {
                case 200...299:
                    do {
                        let loginResponse = try JSONDecoder().decode(LoginResponse.self, from: data!)
                        // loginResponse.access_token
                    } catch {
                        print("Erro ao decodificar resposta de sucesso: \(error)")
                    }
            
                default:
                    do {
                        let errorResponse = try JSONDecoder().decode(ErrorResponse.self, from: data!)
                        print("Erro da API: \(errorResponse.error_description)")
                    } catch {
                        print("Erro ao decodificar resposta de erro: \(error)")
                    }
                }
            }
            
        }
        .resume()
        
    }
    
}

public struct LoginResponse : Decodable {
    var access_token: String
}


public struct ErrorResponse: Decodable {
    var error: String
    var error_description: String
}

public enum LoginError: Error {
    
    case urlNotFound
    
}