//
//  InputLoginView.swift
//  campios
//
//  Created by Camp - 2024 on 23/04/24.
//

import SwiftUI

struct InputLoginView: View {
    var inputText: Binding<String>
    var textFieldValue: String
    
    var body: some View {
        TextField(
            textFieldValue,
            text: inputText
        )
        .padding(.vertical, 13)
        .padding(.leading, 24)
        .background(
            Capsule().fill(.white)
        )
        .padding(.horizontal, 16)
    }
}
