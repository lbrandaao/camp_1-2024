//
//  ContentView.swift
//  campios
//
//  Created by Camp - 2024 on 23/04/24.
//

import SwiftUI

struct LoginView: View {
    
    @StateObject var viewModel = LoginViewModel()
    
    var body: some View {
        ZStack(
            alignment: .top
        ) {
            VStack {
                VStack(spacing: 16) {
                    Text("Seja bem vind@!")
                        .foregroundStyle(.white)
                        .font(.title)
                    
                    Text("HTTP Status Code")
                        .foregroundStyle(.white)
                        .font(.title2)
                }
                .padding(.bottom, 48)
                
                VStack(spacing: 16) {
                    InputLoginView(inputText: $viewModel.username, textFieldValue: "Usuário")
                    InputLoginView(inputText: $viewModel.password, textFieldValue: "Senha")
                }
                .padding(.bottom, 32)
                
                Button {
                    viewModel.enterButtonAction()
                } label: {
                    ZStack {
                        Capsule()
                            .fill(.black)
                            .frame(height: 48)
                        
                        Text("ENTRAR")
                            .foregroundStyle(.white)
                    }
                }
                .padding(.horizontal, 16)
            }
            .frame(
                maxWidth: .infinity,
                maxHeight: .infinity
            )
            .background(
                Color("backgroundLoginColor")
            )
            
            Image("logo_ioasys")
        }
        
    }
}



#Preview {
    LoginView()
}
