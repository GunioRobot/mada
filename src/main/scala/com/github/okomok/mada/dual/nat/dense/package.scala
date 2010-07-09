

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat


package object dense extends dense.Common {
    type  +[x <: Dense, y <: Dense] = x# +[y]
    type  -[x <: Dense, y <: Dense] = x# -[y]
    type **[x <: Dense, y <: Dense] = x# **[y]

    type ===[x <: Dense, y <: Dense] = x# ===[y]
    type !==[x <: Dense, y <: Dense] = x# !==[y]

    type  <[x <: Dense, y <: Dense] = x# <[y]
    type <=[x <: Dense, y <: Dense] = x# <=[y]
    type  >[x <: Dense, y <: Dense] = x# >[y]
    type >=[x <: Dense, y <: Dense] = x# >=[y]
}